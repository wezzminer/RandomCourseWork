/*
 * Wessley Alexander
 *
 * AnimationDemo as a template
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class Exam02 extends JFrame
{

    private static final Random rand = new Random();

    private class Ball
    {

        private static final int ballSize = 6;
        private static final int ballSpeed = 5;

        private double x;
        private double y;
        private double xVelocity;
        private double yVelocity;
        private Color color;
        private int frames;

        public Ball(double ballX, double ballY)
        {
            this.x = ballX;
            this.y = ballY;

            double angle = rand.nextDouble() * 2 * Math.PI;
            xVelocity = Math.cos(angle) * ballSpeed;
            yVelocity = Math.sin(angle) * ballSpeed;
            color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            frames = 750;
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
            yVelocity += .1;
            frames -= 1;

            int wallCheck = checkOnScreen(screenX, screenY);
            if (wallCheck != 0) {
                switch (wallCheck) {
                    case 1: case 2:
                        xVelocity = -xVelocity;
                    case 3: case 4:
                        yVelocity *= .95;
                        yVelocity = -yVelocity;
                }
            }
        }

        public void draw(Graphics g)
        {
            Color oldColor = g.getColor();
            g.setColor(color);
            g.fillOval((int) (x - ballSize / 2), (int) (y - ballSize / 2), ballSize, ballSize);
            g.setColor(oldColor);
        }

        public boolean timesUp() {
            return frames == 0;
        }

    }

    private class DrawingPanel extends JPanel
    {

        private final ArrayList<Ball> balls = new ArrayList<>();
        private final Timer animTimer;

        public DrawingPanel()
        {
            setBackground(Color.black);

            addMouseListener(new MouseAdapter()
            {

                @Override
                public void mousePressed(MouseEvent e)
                {
                    for (int i = 0; i < 50; i++) {
                        balls.add(new Ball(e.getX(), e.getY()));
                    }

                    repaint();
                }

            });

            animTimer = new Timer(16, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    List deadList = new LinkedList();
                    for (Ball b : balls)
                    {
                        b.move(getWidth(), getHeight());
                        if (b.timesUp()) {
                            deadList.add(b);
                        }
                    }
                    balls.removeAll(deadList);
                    repaint();
                }
            });
            animTimer.start();
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            for (Ball b : balls)
            {
                b.draw(g);
            }
        }
    }

    public Exam02()
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
                new Exam02().setVisible(true);
            }
        });
    }

}