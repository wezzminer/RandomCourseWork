package com.wessleyAlexander.Asteroidgame.things;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends ThingObject
{

    float velocityX = 0;
    float velocityY = 0;

    int bulletCooldownFrames = 0;

    boolean doDrawFire = false;

    public Player(ObjectHandler objectHandler, int x, int y)
    {
        super(objectHandler, x, y);
        this.setAngle(Math.toRadians(270));
    }

    @Override
    public void update(boolean[] keys)
    {

        collision();

        if (keys[0]) {this.addToAngle(-.075);}
        if (keys[1]) {this.addToAngle(.075);}
        if (keys[2]) {addVelocity(); doDrawFire = true;}
        if (keys[3] && bulletCooldownFrames == 0) {shootBullet();}

        if (bulletCooldownFrames > 0) {bulletCooldownFrames--;}

        updatePosition();
    }

    @Override
    public void draw(Graphics2D g2d)
    {
        AffineTransform old = g2d.getTransform();

        g2d.rotate(angle, x, y);
        g2d.setColor(Color.WHITE);
        g2d.drawPolygon(getPlayerPolygon());
        if (doDrawFire)
        {
            g2d.setColor(Color.RED);
            g2d.drawPolygon(getPlayerFire());
            doDrawFire = false;
        }


        g2d.setTransform(old);
    }

    private Polygon getPlayerPolygon()
    {
        int[] px = {getXInt() + 20, getXInt() - 20, getXInt() - 20};
        int[] py = {getYInt(), getYInt() + 15, getYInt() - 15};
        int nPoints = 3;

        return new Polygon(px, py, nPoints);
    }

    private Polygon getPlayerFire()
    {
        int[] px = {getXInt() - 21, getXInt() - 21, getXInt() - 35};
        int[] py = {getYInt() + 10, getYInt() - 10, getYInt()};
        int nPoints = 3;

        return new Polygon(px, py, nPoints);
    }

    private void addVelocity()
    {
        velocityX += .3 * Math.cos(angle);
        velocityY += .3 * Math.sin(angle);
    }

    private void updatePosition()
    {
        if (velocityX > 8) { velocityX = 8;}
        if (velocityX < -8) { velocityX = -8;}
        if (velocityY > 8) { velocityY = 8;}
        if (velocityY < -8) { velocityY = -8;}

        x += velocityX;
        y += velocityY;

        // Friction
        velocityX *= .99;
        velocityY *= .99;

        if (x > 900) {x = 0;}
        if (x < 0) {x = 900;}
        if (y > 900) {y = 0;}
        if (y < 0) {y = 900;}
    }

    private void shootBullet()
    {
        int startX = (int) (30 * Math.cos(angle));
        int startY = (int) (30 * Math.sin(angle));

        bulletCooldownFrames += 7;

        objectHandler.addBullet(startX + getXInt(), startY + getYInt(), this.angle,
                true, new float[]{velocityX, velocityY});
    }

    private void collision()
    {
        Rectangle hitbox = getHitBox();

        for (ThingObject shootable: objectHandler.shootables)
        {
            Asteroid obj = (Asteroid) shootable;
            if (hitbox.intersects(obj.getHitBoxes()[0]) | hitbox.intersects(obj.getHitBoxes()[1])
                | hitbox.intersects(obj.getHitBoxes()[2]))
            {

            }
        }

        for (ThingObject bullet : objectHandler.bullets)
        {
            Bullet obj = (Bullet) bullet;
            if (!obj.isPlayers && hitbox.intersects(obj.getHitBox()))
            {
                // DIE
            }
        }

    }

    public Rectangle getHitBox()
    {
        return new Rectangle((int) x - 20, (int) y - 10, 30, 20);
    }

}
