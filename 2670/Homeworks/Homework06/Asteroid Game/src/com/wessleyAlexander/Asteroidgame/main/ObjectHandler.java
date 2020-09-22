package com.wessleyAlexander.Asteroidgame.main;


import com.wessleyAlexander.Asteroidgame.things.*;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class ObjectHandler
{
    /*
     * 0 = left
     * 1 = right
     * 2 = up
     * 3 = space
     * Not a good way to do this, fix later
     */
    public boolean[] keys = {false, false, false, false};

    public LinkedList<ThingObject> gui = new LinkedList<>();
    public LinkedList<ThingObject> players = new LinkedList<>();
    public LinkedList<ThingObject> shootables = new LinkedList<>();
    public LinkedList<ThingObject> bullets = new LinkedList<>();

    LinkedList<ThingObject> addShootables = new LinkedList<>();

    LinkedList<ThingObject> removeShootables = new LinkedList<>();
    LinkedList<ThingObject> removeBullets = new LinkedList<>();

    ObjectHandler() {}

    void updateObjects()
    {
        updateList(bullets);
        updateList(shootables);
        updateList(players);
        updateList(gui);

        tryRemove();
        tryAdd();

        // Sets space to false, fix later
        keys[3] = false;
    }

    // updates each object in list
    void updateList(LinkedList<ThingObject> list)
    {
        for (ThingObject obj : list)
        {
            obj.update(keys);
        }
    }

    void drawObjects(Graphics2D g2d)
    {
        drawList(bullets, g2d);
        drawList(shootables, g2d);
        drawList(players, g2d);
        drawList(gui, g2d);
    }

    void drawList(LinkedList<ThingObject> list, Graphics2D g2d)
    {
        for (ThingObject obj : list)
        {
            obj.draw(g2d);
        }
    }

    private void tryRemove()
    {
       ThingObject obj;

       if (!removeBullets.isEmpty())
       {
           obj = removeBullets.pop();
           bullets.remove(obj);
       }

       if (!removeShootables.isEmpty())
       {
           obj = removeShootables.pop();
           shootables.remove(obj);
       }

    }

    private void tryAdd()
    {
        ThingObject obj;

        if (!addShootables.isEmpty())
        {
            obj = addShootables.pop();
            shootables.add(obj);
        }

    }

    public void addPlayer(int x, int y)
    {
        players.add(new Player(this, x,y));
    }

    public void addAsteroid(int x, int y, int size)
    {
        addShootables.add(new Asteroid(this, x, y, size));
    }

    public void addGui()
    {
        gui.add(new GUI(this, 0, 0));
    }

    public void addBullet(int x, int y, double angle, boolean players, float[] shootersVelocity)
    {
        bullets.add(new Bullet(this, x, y, angle, players, shootersVelocity));
    }

    public void removeShootable(ThingObject obj)
    {
        removeShootables.add(obj);
    }

    public void removeBullet(ThingObject obj)
    {
        removeBullets.add(obj);
    }

}
