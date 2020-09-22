/*
 * Wessley Alexander
 *
 * Exercise01
 *
 * Creates a frame that displays the mouse coordinates
 *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class Exercise01 extends JFrame
{
    private class DrawingPanel extends JPanel
    {

        private final Timer timer;

        private int mouseScreenX;
        private int mouseScreenY;
        private int mousePanelX;
        private int mousePanelY;

        private boolean isOnPanel;
        private boolean isNearLeftEdge;
        private boolean isNearTopEdge;

        public DrawingPanel() {
            setBackground(Color.white);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isOnPanel = true;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isOnPanel = false;
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    mousePanelX = e.getX();
                    mousePanelY = e.getY();

                    isNearLeftEdge = e.getX() >= (getWidth() - 70);
                    isNearTopEdge = e.getY() <= 15;
                }
            });

            timer = new Timer(10, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mouseScreenX = MouseInfo.getPointerInfo().getLocation().x;
                    mouseScreenY = MouseInfo.getPointerInfo().getLocation().y;
                    repaint();
                }
            });
            timer.start();

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawCoords(g);
        }

        private void drawCoords(Graphics g) {
            if (isOnPanel) {
                int textX = mousePanelX;
                int textY = mousePanelY;

                if (isNearLeftEdge) {
                    textX -= 60;
                }
                if (isNearTopEdge) {
                    textY += 30;
                }

                //Text follows mouse
                g.drawString("(" + mouseScreenX + "," + mouseScreenY + ")", textX, textY);

            } else {

                double dx = mouseScreenX - (this.getLocationOnScreen().x + (getWidth()/2));
                double dy = mouseScreenY - (this.getLocationOnScreen().y + (getHeight()/2));
                double angle = Math.atan(dy/dx);

                double tx;
                double ty;

                if (mouseScreenX < (this.getLocationOnScreen().x + (getWidth()/2))) {
                    tx = (getWidth() * .2) * -Math.cos(angle);
                    ty = (getHeight() * .2) * -Math.sin(angle);
                } else {
                    tx = (getWidth() * .2) * Math.cos(angle);
                    ty = (getHeight() * .2) * Math.sin(angle);
                }

                // Draws the text relative to the center of the panel in a circle
                g.drawString("(" + mouseScreenX + "," + mouseScreenY + ")", (int) tx + (getWidth()/2), (int) ty + (getHeight()/2));
            }
        }
    }

    public Exercise01() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        add(new DrawingPanel());
    }

    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(() -> new Exercise01().setVisible(true));
    }

}