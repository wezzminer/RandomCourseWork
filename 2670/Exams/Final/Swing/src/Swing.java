/*
 * Wessley Alexander
 *
 *  FINAL EXAM
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Swing extends JFrame {

    private static final Random rand = new Random();
    int key = 0;
    private final ArrayList<Particle> parts = new ArrayList<>();

    class KeyboardListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    key = 3;
                    break;
                case KeyEvent.VK_RIGHT:
                    key = 1;
                    break;
                case KeyEvent.VK_UP:
                    key = 2;
                    break;
                case KeyEvent.VK_DOWN:
                    key = 0;
                    break;
                case KeyEvent.VK_SPACE:
                    parts.clear();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class Particle
    {

        private double x;
        private double y;
        private double xVelocity;
        private double yVelocity;
        private Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());;
        private int size = 3;

        Particle(int x, int y)
        {
            this.x = x;
            this.y = y;

            switch (key)
            {
                case 0:
                    yVelocity += 5;
                    break;
                case 1:
                    xVelocity += 5;
                    break;
                case 2:
                    yVelocity -= 5;
                    break;
                case 3:
                    xVelocity -= 5;
                    break;
            }

        }

        /**
         * returns 1 if left wall passed
         * returns 2 if right wall passed
         * returns 3 if top wall passed
         * returns 4 if bottom wall passed
         *
         * returns 0 if no conditions are met.
         *
         * @param screenX current screen width
         * @param screenY current screen height
         * @return int 0-4
         */
        public int checkOnScreen(int screenX, int screenY)
        {
            if (x < 0) {
                return 1;
            } else if (x > screenX) {
                return 2;
            } else if (y < 0) {
                return 3;
            } else if (y > screenY){
                return 4;
            } else {
                return 0;
            }

        }

        /**
         *
         * Y velocity approaches one as time increases.
         * hitting any wall multiplies velocity by -1.
         *
         */
        public void move(int screenX, int screenY)
        {
            x += xVelocity;
            y += yVelocity;

            int wallCheck = checkOnScreen(screenX, screenY);
            if (wallCheck != 0) {
                switch (wallCheck) {
                    case 1: case 2:
                        xVelocity = -xVelocity;
                    case 3: case 4:
                        yVelocity = -yVelocity;
                }
            }
        }

        public void draw(Graphics g)
        {
            g.setColor(color);
            g.fillOval((int) (x - size / 2), (int) (y - size / 2), size, size);
        }

    }

    private class DrawingPanel extends JPanel {

        public DrawingPanel()
        {
            setBackground(Color.BLACK);

            addMouseListener(new MouseAdapter()
            {
                @Override
                public void mousePressed(MouseEvent e)
                {
                    parts.add(new Particle(e.getX(), e.getY()));
                }
            });

            addMouseMotionListener(new MouseAdapter()
            {
                @Override
                public void mouseDragged(MouseEvent e)
                {
                    parts.add(new Particle(e.getX(), e.getY()));
                }
            });

            Timer timer = new Timer(16, e -> {
                for (Particle b : parts) {
                    b.move(getWidth(), getHeight());
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            g.setColor(Color.RED);
            g.drawString("Space to clear!", 250, 270);

            for (Particle p : parts)
            {
                p.draw(g);
            }

        }
    }

    public Swing() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 600);
        add(new DrawingPanel());
        addKeyListener(new KeyboardListener());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Swing().setVisible(true));
    }

}