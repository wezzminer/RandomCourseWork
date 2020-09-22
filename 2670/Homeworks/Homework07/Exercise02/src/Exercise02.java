/*
 * Wessley Alexander
 *
 * Exercise02
 *
 * Creates a frame that displays two watchful eyes
 *
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;


public class Exercise02 extends JFrame
{

    private class DrawingPanel extends JPanel {

        class Eye {

            private int eyeX;
            private int eyeY;

            private final int eyeRadius = 100;
            private final int pupilRadius = 20;

            Eye(int panelX, int panelY) {
                this.eyeX = panelX;
                this.eyeY = panelY;
            }

            /**
             * Calculates the distance between the cursor and the center of the eye, then compares
             * the distance to the eye's radius to check if the cursor's inside
             *
             * @return true if distance is smaller than radius,
             *         false if distance is greater than radius.
             */
            public boolean checkIfCursorInEye() {
                double cursorDistanceFromCenter = Math.sqrt( Math.pow(mousePanelX - eyeX, 2) + Math.pow(mousePanelY - eyeY, 2) );
                return cursorDistanceFromCenter <= (eyeRadius - pupilRadius);
            }

            public void draw(Graphics g) {
                g.setColor(Color.white);
                // Eyeball
                g.fillOval(eyeX - eyeRadius, eyeY - eyeRadius, eyeRadius * 2, eyeRadius * 2);
                g.setColor(Color.black);
                // Eyeball outline
                g.drawOval(eyeX - eyeRadius, eyeY - eyeRadius, eyeRadius * 2, eyeRadius * 2);
                drawPupil(g);
            }

            private void drawPupil(Graphics g) {
                if (checkIfCursorInEye()) {
                    //Pupil follows mouse
                    g.fillOval(mousePanelX - pupilRadius, mousePanelY - pupilRadius,
                            pupilRadius*2, pupilRadius*2);
                } else {

                    double dx = mouseScreenX - (getLocationOnScreen().x + eyeX);
                    double dy = mouseScreenY - (getLocationOnScreen().y + eyeY);
                    double angle = Math.atan(dy/dx);

                    double tx;
                    double ty;

                    if (mouseScreenX < (getLocationOnScreen().x + eyeX)) {
                        tx = (eyeRadius - pupilRadius) * -Math.cos(angle);
                        ty = (eyeRadius - pupilRadius) * -Math.sin(angle);
                    } else {
                        tx = (eyeRadius - pupilRadius) * Math.cos(angle);
                        ty = (eyeRadius - pupilRadius) * Math.sin(angle);
                    }

                    // Draws the pupil relative to the center of the eye along its edge.
                    g.fillOval((int) tx + eyeX - pupilRadius, (int) ty + eyeY - pupilRadius,
                            pupilRadius*2, pupilRadius*2);
                }

            }

        }

        ArrayList<Eye> eyeList = new ArrayList<>();

        private int mouseScreenX;
        private int mouseScreenY;
        private int mousePanelX;
        private int mousePanelY;

        public DrawingPanel() {
            setBackground(Color.gray);

            eyeList.add(new Eye(300, 200));
            eyeList.add(new Eye(500, 200));

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePanelX = e.getX();
                    mousePanelY = e.getY();
                }
            });

            Timer timer = new Timer(10, e -> {
                mouseScreenX = MouseInfo.getPointerInfo().getLocation().x;
                mouseScreenY = MouseInfo.getPointerInfo().getLocation().y;
                repaint();
            });
            timer.start();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Eye e : eyeList) {
                e.draw(g);
            }
        }
    }

    public Exercise02() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 400);
        setResizable(false);
        add(new DrawingPanel());
    }

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(() -> new Exercise02().setVisible(true));
    }

}