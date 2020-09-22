package com.wessleyAlexander.Asteroidgame.main;

import com.wessleyAlexander.Asteroidgame.levels.LevelHandler;
import com.wessleyAlexander.Asteroidgame.listeners.KeyboardListener;

import javax.swing.*;

class Asteroid_Game extends JFrame
{

    ObjectHandler objectHandler = new ObjectHandler();
    LevelHandler levelHandler = new LevelHandler(objectHandler);

    public static void main(String[] args)
    {
        new Asteroid_Game();
    }

    Asteroid_Game()
    {
        initialize(900, 900);
    }

    private void initialize(int frameWidth, int frameHeight)
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(frameWidth, frameHeight);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Asteroids : Wessley Alexander");
        setVisible(true);

        add(new DrawingPanel(objectHandler, levelHandler));
        addKeyListener(new KeyboardListener(objectHandler));

        startGame();
    }

    private void startGame()
    {

        levelHandler.testLevel();

    }

}
