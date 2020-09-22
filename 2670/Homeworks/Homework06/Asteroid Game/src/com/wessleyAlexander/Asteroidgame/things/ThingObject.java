package com.wessleyAlexander.Asteroidgame.things;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.*;

public abstract class ThingObject
{
    float x;
    float y;
    double angle = 0;
    ObjectHandler objectHandler;

    ThingObject(ObjectHandler objectHandler, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.objectHandler = objectHandler;
    }

    public abstract void update(boolean[] keys);
    public abstract void draw(Graphics2D g2d);

    public int getXInt()
    {
        return (int) x;
    }

    public int getYInt()
    {
        return (int) y;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public void addToAngle(double angle)
    {
        this.angle += angle;
    }

}
