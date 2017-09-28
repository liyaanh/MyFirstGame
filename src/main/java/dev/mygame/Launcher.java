package dev.mygame;

/*
  NOTE: for a package to properly exist, each word between
  periods must be their own directories that follow the parent.child
  relationship defined.
*/

import dev.mygame.display.Display;

public class Launcher
{
    public static void main(String[] args)
    {
        Game game = new Game("Tile Game!", 640, 480);
        game.start();
    }
}