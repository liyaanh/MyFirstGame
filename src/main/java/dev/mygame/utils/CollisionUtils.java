package dev.mygame.utils;

import dev.mygame.entities.shapes.Rectangle;

public class CollisionUtils
{
	public static boolean isCollisionUp(Rectangle r1, Rectangle r2)
	{
		return r1.getY() < r2.getY() + r2.getHeight();
	}
	
	public static boolean isCollisionDown(Rectangle r1, Rectangle r2)
	{
		return r1.getY() + r1.getHeight() > r2.getY();
	}
	
	public static boolean isCollisionLeft(Rectangle r1, Rectangle r2)
	{
		return r1.getX() < r2.getX() + r2.getWidth();
	}
	
	public static boolean isCollisionRight(Rectangle r1, Rectangle r2)
	{
		return r1.getX() + r1.getWidth() > r2.getX();
	}
	
	public static boolean isCollision(Rectangle r1, Rectangle r2)
	{
		if((isCollisionLeft(r1, r2) && isCollisionUp(r1, r2)) // top left
			|| (isCollisionRight(r1, r2) && isCollisionUp(r1, r2)) // top right
			|| (isCollisionLeft(r1, r2) && isCollisionDown(r1, r2)) // bottom left
			|| (isCollisionRight(r1, r2) && isCollisionDown(r1, r2))) // bottom right
		{
			return true;
		}
		return false;
	}
}