package org.example;

import java.awt.*;

public class Rectangle extends Shape {
    public Rectangle(int x1, int y1, int x2, int y2, Color color, int brushSize) {
        super(x1, y1, x2, y2, color, brushSize);
    }

    @Override
    public void draw(Graphics2D g2d) {
        int width = Math.abs(x2 - x1);
        int height = Math.abs(y2 - y1);
        int upperLeftX = Math.min(x1, x2);
        int upperLeftY = Math.min(y1, y2);

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawRect(upperLeftX, upperLeftY, width, height);
    }
}