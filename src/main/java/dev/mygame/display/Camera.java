package dev.mygame.display;

public class Camera
{
	private int x;
	private int y;
	private int w;
	private int h;
	private int focusX;
	private int focusY;
	
	public Camera(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getFocusX()
	{
		return focusX;
	}
	
	public void setFocus(int focusX, int focusY)
	{
		this.x = focusX - w/2;
		this.y = focusY - h/2;
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