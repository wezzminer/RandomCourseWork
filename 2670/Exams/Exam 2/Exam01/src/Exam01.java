/*
 * CSM 2670 Exam 01
 *
 * Name: Wessley Alexander
 * File: Exam01.java
 *
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;

public class Exam01
{

    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
            setBackground(Color.black);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    private static JButton saveButton() {
        return new JButton("Save");
    }

    private static JButton clearButton() {
        return new JButton("Clear");
    }

    public Exam01() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        JPanel buttons = new JPanel();
        buttons.add(saveButton());
        buttons.add(clearButton());

        JPanel fullPanel = new JPanel();

        fullPanel.add(buttons, BorderLayout.CENTER);
        fullPanel.add(new DrawingPanel());
        fullPanel.setLayout(new BoxLayout(fullPanel, BoxLayout.PAGE_AXIS));

        frame.add(fullPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Exam01 window = new Exam01();
    }

}