package com.wessleyAlexander.Asteroidgame.things;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.*;

public class GUI extends ThingObject
{

    public GUI(ObjectHandler objectHandler, int x, int y)
    {
        super(objectHandler, x, y);
    }


    @Override
    public void update(boolean[] keys)
    {

    }

    @Override
    public void draw(Graphics2D g2d)
    {
        g2d.setColor(Color.RED);
        g2d.drawString("Prototype", 830, 15);
    }
}
