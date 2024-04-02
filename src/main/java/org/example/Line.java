package org.example;

import java.awt.*;

class SimpleLine extends Shape {
    public SimpleLine(int x1, int y1, int x2, int y2, Color color, int brushSize) {
        super(x1, y1, x2, y2, color, brushSize);
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(brushSize));
        g2d.drawLine(x1, y1, x2, y2);
    }
}