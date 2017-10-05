package dev.mygame.display;

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
	
	public void setFocus(float focusX, float focusY)
	{
		float lerp = 1.0f;
		x += lerp * (focusX - w/2 - x);
		y += lerp * (focusY - h/2 - y);
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
	
	public void tick()
	{
		// centre the camera on the focus point
		//focusX -= w/2;
		//focusY -= h/2;
		// set the origin so we can offset everything else
		x = focusX;
		y = focusY;
	}
}