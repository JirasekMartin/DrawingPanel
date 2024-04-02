package org.example;

import javax.swing.*;
import java.awt.*;

public class AdvancedPaintApp extends JFrame {
    public AdvancedPaintApp() {
        setTitle("Advanced Paint App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setDrawingMode("Line"); // Nastavení výchozího kreslícího režimu na Line
        add(drawingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        String[] drawingModes = {"Line", "Rectangle", "Ellipse"};
        JComboBox<String> modeComboBox = new JComboBox<>(drawingModes);
        modeComboBox.addActionListener(e -> {
            drawingPanel.setDrawingMode((String) modeComboBox.getSelectedItem());
        });

        JButton colorButton = new JButton("Choose Color");
        colorButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "Choose Color", Color.BLACK);
            drawingPanel.setCurrentColor(selectedColor);
        });

        JSlider brushSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 20, 5);
        brushSizeSlider.addChangeListener(e -> {
            drawingPanel.setBrushSize(brushSizeSlider.getValue());
        });

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            drawingPanel.clear(); // Vyvolání metody pro vymazání kresby
        });

        controlPanel.add(new JLabel("Mode:"));
        controlPanel.add(modeComboBox);
        controlPanel.add(colorButton);
        controlPanel.add(new JLabel("Brush Size:"));
        controlPanel.add(brushSizeSlider);
        controlPanel.add(resetButton); // Přidání tlačítka na reset

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdvancedPaintApp().setVisible(true));
    }
}