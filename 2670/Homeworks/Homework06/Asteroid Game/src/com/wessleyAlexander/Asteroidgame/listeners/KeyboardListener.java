package com.wessleyAlexander.Asteroidgame.listeners;

import com.wessleyAlexander.Asteroidgame.main.ObjectHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    ObjectHandler objectHandler;

    public KeyboardListener(ObjectHandler objectHandler)
    {
        this.objectHandler = objectHandler;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                objectHandler.keys[0] = true;
                break;
            case KeyEvent.VK_RIGHT:
                objectHandler.keys[1] = true;
                break;
            case KeyEvent.VK_UP:
                objectHandler.keys[2] = true;
                break;
            case KeyEvent.VK_SPACE:
                objectHandler.keys[3] = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                objectHandler.keys[0] = false;
                break;
            case KeyEvent.VK_RIGHT:
                objectHandler.keys[1] = false;
                break;
            case KeyEvent.VK_UP:
                objectHandler.keys[2] = false;
                break;
        }
    }
}
