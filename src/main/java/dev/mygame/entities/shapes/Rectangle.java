package dev.mygame.entities.shapes;

import java.awt.Graphics;
import java.awt.Color;

import dev.mygame.Handler;
import dev.mygame.entities.Entity;

public class Rectangle extends Entity
{
	public Rectangle(Handler handler, float x, float y, int w, int h)
	{
		super(handler, x, y, w, h);
	}
	
	public void update(float x, float y)
	{
		this.x = x - width / 2;
		this.y = y - height / 2;
	}
	
	@Override
	public void tick()
	{
		
	}
	
	@Override
	public void render(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillRect((int)(x - handler.getCamera().getX()), (int)(y - handler.getCamera().getY()), width, height);
	}
}