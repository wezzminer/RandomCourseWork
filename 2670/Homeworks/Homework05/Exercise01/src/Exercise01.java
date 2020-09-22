/*
 * CSM 2670 Exercise 01
 *
 * Name: Wessley Alexander
 * File: Exercise01.java
 *
 * Draws a pyramid with a JPanel.
 *
 * Template: "Making a Panel for drawing"
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Exercise01 extends JFrame
{

    final static int NUMBER_OF_ROWS = 10;

    private class DrawingPanel extends JPanel
    {

        public DrawingPanel()
        {
            setBackground(Color.white);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.setColor(Color.blue);
            g.fillRect((int) (getWidth() * .1), (int) (getHeight() * .1), (int) (getWidth() * .8), (int) (getHeight() * .8));

            for (int row = 1; row <= NUMBER_OF_ROWS; row++) {

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
    }

    public Exercise01()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        add(new DrawingPanel());
    }

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                new Exercise01().setVisible(true);
            }
        });
    }

}