package com.wessleyAlexander.Asteroidgame.things;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.*;
import java.util.Random;

public class Asteroid extends ThingObject
{

    float velocityX = 0;
    float velocityY = 0;

    int size;

    boolean destroyed = false;

    public Asteroid(ObjectHandler objectHandler, int x, int y, int size)
    {
        super(objectHandler, x, y);
        this.size = size;
        Random rand = new Random();
        this.angle = Math.toRadians(rand.nextInt(360));
        setVelocity();
    }

    @Override
    public void update(boolean[] keys)
    {
        if (!destroyed)
        {
            updatePosition();
            collision();
        }
    }

    @Override
    public void draw(Graphics2D g2d)
    {

        g2d.setColor(getColor());
        g2d.drawPolygon(getPolygon());

    }

    private Polygon getPolygon()
    {
        int[] px = {getXInt() + (10 * size), getXInt() - (10 * size), getXInt() - (20 * size),
                getXInt() - (10 * size), getXInt() + (10 * size), getXInt() + (20 * size)};
        int[] py = {getYInt() + (20 * size), getYInt() + (20 * size), getYInt(),
                getYInt() - (20 * size), getYInt() - (20 * size), getYInt()};
        int nPoints = 6;

        return new Polygon(px, py, nPoints);
    }

    private void setVelocity()
    {
        velocityX += 3 * Math.cos(angle);
        velocityY += 3 * Math.sin(angle);
    }

    private void updatePosition()
    {
        x += velocityX;
        y += velocityY;

        if (x > 900) {x = 0;}
        if (x < 0) {x = 900;}
        if (y > 900) {y = 0;}
        if (y < 0) {y = 900;}
    }

    private void collision()
    {
        Rectangle[] hitboxes = getHitBoxes();

        for (ThingObject bullet : objectHandler.bullets)
        {
            Bullet obj = (Bullet) bullet;
            if (obj.isPlayers && ( hitboxes[0].intersects(obj.getHitBox())
                                    | hitboxes[1].intersects(obj.getHitBox())
                                    | hitboxes[2].intersects(obj.getHitBox())))
            {
                objectHandler.removeBullet(obj);
                this.popRock();
                destroyed = true;
            }
        }

    }

    public Rectangle[] getHitBoxes()
    {
        return new Rectangle[]{new Rectangle(getXInt() - (10 * size), getYInt() - (20 * size),
                                        (20 * size), (40 * size)),
                                new Rectangle(getXInt() - (15 * size), getYInt() - (12 * size),
                                        (5 * size), (25 * size)),
                                new Rectangle(getXInt() + (10 * size), getYInt() - (12 * size),
                                        (5 * size), (25 * size))};
    }

    private void popRock()
    {
        if (size > 1)
        {
            objectHandler.addAsteroid(getXInt(), getYInt(), this.size - 1);
            objectHandler.addAsteroid(getXInt(), getYInt(), this.size - 1);
        }
        objectHandler.removeShootable(this);
    }

    private Color getColor()
    {
        return Color.WHITE;
    }

}
