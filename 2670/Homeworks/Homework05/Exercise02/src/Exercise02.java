/*
 * CSM 2670 Exercise 02
 *
 * Name: Wessley Alexander
 * File: Exercise02.java
 *
 * Animates a pyramid with a JPanel.
 *
 * Template: "Making a Panel for drawing", "Swing Demo"
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Exercise02 extends JFrame
{

    private final DrawingPanel dPanel;

    private static class DrawingPanel extends JPanel
    {

        final static int NUMBER_OF_ROWS = 40;

        public DrawingPanel()
        {
            setBackground(Color.white);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            drawPrettyPicture(g);
        }

        private void drawPrettyPicture(Graphics g) {

            g.setColor(Color.blue);
            g.fillRect((int) (getWidth() * .1), (int) (getHeight() * .1), (int) (getWidth() * .8), (int) (getHeight() * .8));

            // Will draw up to the amount of rows given in rowsToDraw
            for (int row = 1; row <= rowsToDraw; row++) {

                // Intermediate heights and widths to accurately place blocks.
                int sectionHeight = (int) ((getHeight() * .8 * .6) / NUMBER_OF_ROWS);
                int widthSections = (int) ((getWidth() * .64) / (NUMBER_OF_ROWS * 2));

                int rowX = (int) (getWidth() * .18) + (widthSections * (NUMBER_OF_ROWS - row));
                int rowY = (int) (getHeight() * .26) + ((row-1) * sectionHeight);
                int rowWidth = (widthSections * 2) * row;

                g.setColor(Color.orange);
                g.fillRect(rowX, rowY, rowWidth, sectionHeight);

                g.setColor(Color.RED);
                g.drawRect(rowX, rowY, rowWidth, sectionHeight);

                for (int block = 1; block <= row; block++) {
                    int lineX = (int) (getWidth() * .18) + (widthSections * (NUMBER_OF_ROWS - row + (2 * block)));
                    g.drawLine(lineX, rowY, lineX, rowY + sectionHeight);
                }

            }

        }

        private int rowsToDraw = 0;
        private int delta = 1;

        private void drawPyramid() {

            rowsToDraw += delta;

            if (rowsToDraw >= NUMBER_OF_ROWS || rowsToDraw <= 0) {
                delta *= -1;
            }

            repaint();

            try
            {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }

        }

    }

    public Exercise02()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        dPanel = new DrawingPanel();
        add(dPanel);
    }

    public static void main(String[] args)
    {

        Exercise02 ExPan =  new Exercise02();

        java.awt.EventQueue.invokeLater(() ->
        {
            ExPan.setVisible(true);
        });

        while (true) {
            ExPan.dPanel.drawPyramid();
        }

    }

}