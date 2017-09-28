package dev.mygame.states;

import java.awt.Graphics;

import dev.mygame.gfx.Assets;
import dev.mygame.Game;

public class MenuState extends State
{
	public MenuState(Game game)
	{
		super(game);
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.playerTwo, 0, 0, null);
	}
}