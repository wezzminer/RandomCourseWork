/*
 * CSM 2670 Exercise 08
 *
 * Name: Wessley Alexander
 * File: Exercise08.java
 *
 * Template: "Making a Panel for drawing"
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Exercise08 extends JFrame
{

    private static int gridHeight = 50;
    private static int gridWidth = 50;
    private Random rand = new Random();
    private final ArrayList<SwitchSquare> gridSquares = new ArrayList<>();
    private int stuckCount = 0;

    private class SwitchSquare {

        private final int gridX;
        private final int gridY;
        private boolean isActive;
        private boolean isDead;

        SwitchSquare(int gridX, int gridY) {

            this.gridX = gridX;
            this.gridY = gridY;
            this.isActive = false;

        }

        public void draw(Graphics g) {

            if (isActive) {
                g.setColor(Color.red);
            } else if (isDead) {
                g.setColor(Color.white);
            } else {
                g.setColor(Color.black);
            }
            g.fillRect(gridX * gridWidth, gridY * gridHeight, gridWidth, gridHeight);
        }

        public void flick() {
            isActive = !isActive;
        }

        public void kill() {
            isDead = !isDead;
        }

        public void move() {

            if (!isActive) {
                return;
            }

            int[] nextCoords = {getGridX(), getGridY()};

            switch (rand.nextInt(4)) {
                case 0:
                    nextCoords[0] += 1;
                    break;
                case 1:
                    nextCoords[0] -= 1;
                    break;
                case 2:
                    nextCoords[1] += 1;
                    break;
                case 3:
                    nextCoords[1] -= 1;
                    break;
            }

            if (!checkNewCoords(nextCoords)) {
                stuckCount += 1;
                return;
            } else {
                for (SwitchSquare s : gridSquares) {
                    if (nextCoords[0] == s.getGridX() && nextCoords[1] == s.getGridY()) {
                        if (s.getIsDead()) {
                            stuckCount += 1;
                            return;
                        }
                        s.flick();
                        this.flick();
                        this.kill();
                        stuckCount = 0;
                    }
                }
            }


        }

        private boolean checkNewCoords(int[] coords) {

            int totalGridWidth = (getWidth() / gridWidth) + 1;
            int totalGridHeight = (getHeight() / gridHeight) + 1;

            if (coords[0] > totalGridWidth || coords[0] < 0) {
                return false;
            } else if (coords[1] > totalGridHeight || coords[1] < 0) {
                return false;
            } else {
                return true;
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

        public boolean getIsActive() {
            return isActive;
        }

        public boolean getIsDead() {
            return isDead;
        }
    }


    private class DrawingPanel extends JPanel
    {

        private final Timer timer;
        private boolean init = true;

        public DrawingPanel()
        {
            setBackground(Color.white);

            timer = new Timer(100, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (stuckCount == 10) {
                        reset();
                        stuckCount = 0;
                    }

                    for (SwitchSquare s : gridSquares) {
                        if (s.getIsActive()) {
                            s.move();
                            break;
                        }
                    }
                    repaint();
                }
            });
            timer.start();
        }

        private void reset() {
            init = true;
            gridSquares.clear();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            if (init) {
                initSquares();
            }

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

        private void initSquares() {
            int totalGridWidth = (getWidth() / gridWidth) + 1;
            int totalGridHeight = (getHeight() / gridHeight) + 1;

            for (int currentX = 0; currentX < totalGridWidth; currentX++) {
                for (int currentY = 0; currentY < totalGridHeight; currentY++) {
                    gridSquares.add(new SwitchSquare(currentX, currentY));
                }
            }

            int startingSquareNumber = totalGridHeight / 2;

            for (SwitchSquare switchSquare : gridSquares) {
                if (startingSquareNumber == switchSquare.getGridX() && startingSquareNumber == switchSquare.getGridY()) {
                    switchSquare.flick();
                }
            }

            init = false;

        }


    }

    public Exercise08()
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
                new Exercise08().setVisible(true);
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