package dev.mygame.entities.creatures;

import java.awt.Graphics;

import dev.mygame.gfx.Assets;
import dev.mygame.Handler;

public class Player extends Creature
{	
	public Player(Handler handler, float x, float y)
	{
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
	}
	
	@Override
	public void tick()
	{
		getInput();
		move();
	}
	
	private void getInput()
	{
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
		{
			yMove = -speed;
		}
		if(handler.getKeyManager().down)
		{
			yMove = +speed;
		}
		if(handler.getKeyManager().left)
		{
			xMove = -speed;
		}
		if(handler.getKeyManager().right)
		{
			xMove = +speed;
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.playerOne, (int)(x - handler.getCamera().getX()), (int)(y - handler.getCamera().getY()), width, height, null);
	}
}