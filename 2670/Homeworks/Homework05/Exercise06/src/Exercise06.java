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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Exercise06 extends JFrame
{

    Random rand = new Random();
    private static int gridHeight = 50;
    private static int gridWidth = 50;

    private class SwitchSquare {

        private final int gridX;
        private final int gridY;
        private boolean isOn;


        SwitchSquare(int gridX, int gridY) {

            this.gridX = gridX;
            this.gridY = gridY;
            this.isOn = false;

        }

        public void draw(Graphics g) {

            if (isOn) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.black);
            }
            g.fillRect(gridX * gridWidth, gridY * gridHeight, gridWidth, gridHeight);

        }

        public void flick() {
            if (this.isOn) {
                isOn = false;
            } else {
                isOn = true;
            }
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

        private final ArrayList<SwitchSquare> gridSquares = new ArrayList<>();

        public DrawingPanel()
        {
            setBackground(Color.white);

            addMouseListener(new MouseAdapter()
            {

                @Override
                public void mousePressed(MouseEvent e)
                {
                    for (SwitchSquare switchSquare : gridSquares) {
                        if (e.getX() >= (switchSquare.getGridX() * gridWidth)
                                && e.getX() < (switchSquare.getGridX() * gridWidth) + gridWidth) {
                            if (e.getY() >= (switchSquare.getGridY() * gridHeight)
                                    && e.getY() < (switchSquare.getGridY() * gridHeight) + gridHeight) {
                                switchSquare.flick();
                            }

                        }
                    }
                    repaint();
                }

                

            });

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

            for (SwitchSquare switchSquare : gridSquares) {
                switchSquare.draw(g);
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
                    for (SwitchSquare switchSquare : gridSquares) {
                        if (currentX == switchSquare.getGridX() && currentY == switchSquare.getGridY()) {
                            doesExist = true;
                        }
                    }

                    if (!doesExist) {
                        gridSquares.add(new SwitchSquare(currentX, currentY));
                    }
                }
            }
        }


    }

    public Exercise06()
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
                new Exercise06().setVisible(true);
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