/*
 * CSM 2670 Exercise 03
 *
 * Name: Wessley Alexander
 * File: Exercise03.java
 *
 * Create a checkerboard that scales with the window!
 *
 * Template: "Making a Panel for drawing"
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Exercise03 extends JFrame
{
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
            drawCheckerBoard(g);
        }

        private void drawCheckerBoard(Graphics g) {

            int[] rectDimensions = new int[3];

            if (getHeight() >= getWidth()) {
                rectDimensions[0] = 0;                              //X
                rectDimensions[1] = (getHeight() - getWidth()) /2;  //Y
                rectDimensions[2] = getWidth();                     //Width/Height
            } else {
                rectDimensions[0] = (getWidth() - getHeight()) / 2; //X
                rectDimensions[1] = 0;                              //Y
                rectDimensions[2] = getHeight();                    //Width/Height
            }

            for (int row = 0; row < 8; row++) {

                for (int column = 0; column < 8; column++) {
                    // Size of each square in the grid
                    int gridSize = rectDimensions[2] / 8;

                    // Draws a square if the row and column are not both even or odd
                    if (row % 2 != column % 2) {
                        g.setColor(Color.gray);
                        g.fillRect(rectDimensions[0] + (gridSize * column), rectDimensions[1] + (gridSize * row),
                                gridSize, gridSize);
                        g.setColor(Color.black);
                        g.drawRect(rectDimensions[0] + (gridSize * column), rectDimensions[1] + (gridSize * row),
                                gridSize, gridSize);

                        // Draws the checker pieces in the first and last three rows
                        if (row < 3) {
                            g.setColor(Color.red);
                            g.fillOval(rectDimensions[0] + (gridSize * column) + (int) (gridSize * .1),
                                    rectDimensions[1] + (gridSize * row) + (int) (gridSize * .1),
                                    (int) (gridSize * .8), (int) (gridSize * .8));
                        } else if (row > 4) {
                            g.setColor(Color.black);
                            g.fillOval(rectDimensions[0] + (gridSize * column) + (int) (gridSize * .1),
                                    rectDimensions[1] + (gridSize * row) + (int) (gridSize * .1),
                                    (int) (gridSize * .8), (int) (gridSize * .8));
                        }
                    }
                }
            }
            // Outline
            g.drawRect(rectDimensions[0], rectDimensions[1], rectDimensions[2], rectDimensions[2]);

        }
    }

    public Exercise03()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        add(new DrawingPanel());
    }

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                new Exercise03().setVisible(true);
            }
        });
    }

}