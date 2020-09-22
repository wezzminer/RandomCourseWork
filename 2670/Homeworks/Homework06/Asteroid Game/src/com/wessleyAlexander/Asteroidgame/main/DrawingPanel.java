package com.wessleyAlexander.Asteroidgame.main;

import com.wessleyAlexander.Asteroidgame.levels.LevelHandler;

import javax.swing.*;
import java.awt.*;

/**
 *
 *
 */
class DrawingPanel extends JPanel
{

    ObjectHandler objectHandler;
    LevelHandler levelHandler;

    DrawingPanel(ObjectHandler objectHandler, LevelHandler levelHandler)
    {
        this.objectHandler = objectHandler;
        this.levelHandler = levelHandler;

        run();
    }

    public void run()
    {

        // I would use a tick based clock, but I feel that would be overkill for an assignment.
        // ~60 FPS
        Timer timer = new Timer(16, e -> {
            update();
            repaint();
        });
        timer.start();

    }

    private void update()
    {
        // call to update all ThingObjects
        objectHandler.updateObjects();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        // Background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, getWidth(), getHeight());

        // call to draw all ThingObjects
        objectHandler.drawObjects(g2d);
    }

}
