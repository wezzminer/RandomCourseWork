/*
 * CSM 2670 Exercise 04
 *
 * Name: Wessley Alexander
 * File: Exercise04.java
 *
 * Create a grid filled with colored squares!
 *
 * Template: "Making a Panel for drawing"
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Exercise04 extends JFrame
{

    Random rand = new Random();
    private static int gridHeight = 50;
    private static int gridWidth = 50;

    private class ColoredSquare {

        private final int gridX;
        private final int gridY;
        private final Color color;

        ColoredSquare(int gridX, int gridY) {

            this.gridX = gridX;
            this.gridY = gridY;
            this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

        }

        public void draw(Graphics g) {

            g.setColor(color);
            g.fillRect(gridX * gridWidth, gridY * gridHeight, gridWidth, gridHeight);

        }

        @Override
        public String toString() {
            return "(" + gridX + "," + gridY + ")";
        }

        public int getGridX() {
            return gridX;
        }

        public int getGridY() {
            return gridY;
        }
    }


    private class DrawingPanel extends JPanel
    {

        private final ArrayList<ColoredSquare> gridSquares = new ArrayList<>();

        public DrawingPanel()
        {
            setBackground(Color.white);
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            createSquares();
            drawSquares(g);
            drawGrid(g);
        }

        private void drawGrid(Graphics g) {

            g.setColor(Color.gray);
            // Total amount of lines that can fit in the window along x and y
            int totalGridWidth = getWidth() / gridWidth;
            int totalGridHeight = getHeight() / gridHeight;

            // Draw vertical gridlines
            for (int line = 1; line <= totalGridWidth; line++) {
                g.drawLine(line * gridWidth, 0, line * gridWidth, getHeight());
            }

            // Draw horizontal gridlines
            for (int line = 1; line <= totalGridHeight; line++) {
                g.drawLine(0, line * gridHeight, getWidth(), line * gridHeight);
            }

        }

        private void drawSquares(Graphics g) {

            for (ColoredSquare coloredSquare : gridSquares) {
                coloredSquare.draw(g);
            }

        }

        private void createSquares() {

            // Total amount of grid boxes that can fit in the window along x and y
            int totalGridWidth = (getWidth() / gridWidth) + 1;
            int totalGridHeight = (getHeight() / gridHeight) + 1;

            for (int currentX = 0; currentX < totalGridWidth; currentX++) {

                for (int currentY = 0; currentY < totalGridHeight; currentY++) {

                    // checks the arraylist for a square with matching coords, if not, add it to the list
                    boolean doesExist = false;
                    for (ColoredSquare coloredSquare : gridSquares) {
                        if (currentX == coloredSquare.getGridX() && currentY == coloredSquare.getGridY()) {
                            doesExist = true;
                        }
                    }

                    if (!doesExist) {
                        gridSquares.add(new ColoredSquare(currentX, currentY));
                    }
                }
            }
        }


    }

    public Exercise04()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        add(new DrawingPanel());
    }

    public static void main(String[] args)
    {

        if (args.length != 0 && args.length != 2) {
            System.err.println("Invalid inputs! Expected two whole numbers.");
            System.exit(1);
        } else if (args.length == 2) {
            gridWidth = testInput(args[0]);
            gridHeight = testInput(args[1]);
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                new Exercise04().setVisible(true);
            }
        });
    }

    private static int testInput(String input) {

        int testedInput = 0;

        try {
            testedInput = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.err.println("Input \"" + input + "\" is not an whole number.");
            System.exit(2);
        }

        if (testedInput < 1) {
            System.err.println("Input \"" + testedInput + "\" is invalid, must be above 0.");
            System.exit(3);
        }

        return testedInput;

    }

}