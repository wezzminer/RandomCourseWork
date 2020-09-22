package com.wessleyAlexander.Asteroidgame.levels;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.util.Random;

public class LevelHandler
{

    ObjectHandler objectHandler;
    Random rand = new Random();

    public LevelHandler(ObjectHandler objectHandler)
    {
        this.objectHandler = objectHandler;
    }

    public void testLevel()
    {
        objectHandler.addGui();

        objectHandler.addPlayer(450,450);
        objectHandler.addAsteroid(rand.nextInt(400), rand.nextInt(400), 3);
        objectHandler.addAsteroid(600, 700, 3);
        objectHandler.addAsteroid(rand.nextInt(400), rand.nextInt(400), 3);
        objectHandler.addAsteroid(800, 800, 3);
    }

}
