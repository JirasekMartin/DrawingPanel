package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private int startX, startY, endX, endY;
    private Color currentColor = Color.BLACK;
    private String drawingMode = "Rectangle"; // Default mode
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private int brushSize = 1; // Default brush size

    public DrawingPanel() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                endX = e.getX();
                endY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();

                Shape shape = createShape();
                if (shape != null) {
                    shapes.add(shape);
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                repaint(); // Překreslit panel pro zobrazení aktualizace tvaru
            }
        });
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    public void setDrawingMode(String mode) {
        drawingMode = mode;
    }

    public void setBrushSize(int size) {
        brushSize = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Shape shape : shapes) {
            shape.draw(g2d);
        }

        if ("Rectangle".equals(drawingMode)) {
            int width = Math.abs(endX - startX);
            int height = Math.abs(endY - startY);
            int upperLeftX = Math.min(startX, endX);
            int upperLeftY = Math.min(startY, endY);
            g2d.setColor(currentColor);
            g2d.drawRect(upperLeftX, upperLeftY, width, height);
        } else if ("Ellipse".equals(drawingMode)) {
            int width = Math.abs(endX - startX);
            int height = Math.abs(endY - startY);
            int upperLeftX = Math.min(startX, endX);
            int upperLeftY = Math.min(startY, endY);
            g2d.setColor(currentColor);
            g2d.drawOval(upperLeftX, upperLeftY, width, height);
        } else if ("Line".equals(drawingMode)) {
            g2d.setColor(currentColor);
            g2d.drawLine(startX, startY, endX, endY);
        }
    }

    private Shape createShape() {
        Shape shape = null;
        int x1 = Math.min(startX, endX);
        int y1 = Math.min(startY, endY);
        int x2 = Math.max(startX, endX);
        int y2 = Math.max(startY, endY);

        switch (drawingMode) {
            case "Line":
                shape = new SimpleLine(startX, startY, endX, endY, currentColor, brushSize);
                break;
            case "Rectangle":
                shape = new Rectangle(x1, y1, x2, y2, currentColor, brushSize);
                break;
            case "Ellipse":
                shape = new Ellipse(x1, y1, x2, y2, currentColor, brushSize);
                break;
        }
        return shape;
    }
    public void clear() {
        shapes.clear();
        repaint(); // Okamžitá aktualizace panelu po vyčištění tvarů
    }
}