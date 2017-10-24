package dev.mygame.display;

import dev.mygame.entities.Entity;

public class Camera
{
	private float x;
	private float y;
	private int w;
	private int h;
	private int focusX;
	private int focusY;
	
	public Camera(float x, float y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
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
	
	public int getFocusX()
	{
		return focusX;
	}
	
	public void setFocus(Entity e)
	{
		float lerp = 1.0f;
		x = lerp * (e.getX() + e.getWidth()/2 - w/2);
		y = lerp * (e.getY() + e.getHeight()/2 - h/2);
		//System.out.println("X :" + x + "\tY :" + y);
	}
	
	public int getFocusY()
	{
		return focusY;
	}
	
	public int getWidth()
	{
		return w;
	}
	
	public int getHeight()
	{
		return h;
	}
}