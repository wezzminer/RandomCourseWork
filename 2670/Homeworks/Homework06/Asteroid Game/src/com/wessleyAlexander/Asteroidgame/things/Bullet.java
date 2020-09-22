package com.wessleyAlexander.Asteroidgame.things;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.*;

public class Bullet extends ThingObject
{

    float velocityX = 0;
    float velocityY = 0;

    int frames = 150;

    public boolean isPlayers;

    public Bullet(ObjectHandler objectHandler, int x, int y, double angle, boolean players, float[] shootersVelocity)
    {
        super(objectHandler, x, y);
        this.angle = angle;
        this.isPlayers = players;
        setVelocity(shootersVelocity);
    }

    @Override
    public void update(boolean[] keys)
    {
        frames -= 1;
        if (frames <= 0) {objectHandler.removeBullet(this);}
        updatePosition();
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        if (isPlayers) {
            g2d.setColor(Color.WHITE);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.fillOval(getXInt(), getYInt(), 3, 3);

    }

    private void setVelocity(float[] shootersVelocity)
    {
        velocityX += (7 * Math.cos(angle)) + shootersVelocity[0];
        velocityY += (7 * Math.sin(angle)) + shootersVelocity[1];
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

    public Rectangle getHitBox()
    {
        return new Rectangle(getXInt(), getYInt(), 3, 3);
    }

}
