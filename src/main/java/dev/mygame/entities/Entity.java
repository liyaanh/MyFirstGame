package dev.mygame.entities;

import java.awt.Graphics;

import dev.mygame.Handler;

public abstract class Entity
{
	protected float x, y;
	protected int width, height;
	
	protected Handler handler;
	
	public Entity(Handler handler, float x, float y, int width, int height)
	{
		this.handler = handler;
		this.x = x;
		this.y = y;
		System.out.println("X : " + x + "\nY : " + y);
		this.width = width;
		this.height = height;
	}
	
	public float getX()
	{
		return x;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}