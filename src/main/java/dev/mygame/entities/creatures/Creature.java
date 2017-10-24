package dev.mygame.entities.creatures;

import dev.mygame.entities.Entity;
import dev.mygame.Handler;
import dev.mygame.tiles.Tile;

public abstract class Creature extends Entity
{
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_WIDTH = 64;
	public static final int DEFAULT_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected float xMove;
	protected float yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height)
	{
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		xMove = 0f;
		yMove = 0f;
	}
	
	public void move()
	{
		//System.out.println("X-Move: " + xMove);
		//x += xMove;
		//y += yMove;
		moveX();
		moveY();
	}
	
	public void moveX()
	{
		if(xMove > 0) // right
		{
			// adding 1 stops it an extra pixel outside of the character width before the edge
			int tX = (int) (x + xMove + width) / Tile.WIDTH;
			
			if(!collisionWithTile(tX, (int) y / Tile.HEIGHT) && 
				!collisionWithTile(tX, (int) (y + height) / Tile.HEIGHT))
			{
				x += xMove;
			} else
			{
				System.out.println("tX: " + tX);
				
				// subtracting 1 puts it an extra pixel outside of character width from the edge
				x = (tX - 1) * Tile.WIDTH;
				System.out.println("X: " + x + " width: " + (x+width) + " Y: " + y);
			}
		} else if(xMove < 0) // left
		{
			int tX = (int) (x + xMove) / Tile.WIDTH;
			
			if(!collisionWithTile(tX, (int) y / Tile.HEIGHT) && 
				!collisionWithTile(tX, (int) (y + height) / Tile.HEIGHT))
			{
				x += xMove;
			}
		}
	}
	
	public void moveY()
	{
		if(yMove > 0) // down
		{
			int tY = (int) (y + yMove + height + 1) / Tile.HEIGHT;
			
			if(!collisionWithTile((int) x / Tile.WIDTH, tY) && 
				!collisionWithTile((int) (x + width) / Tile.WIDTH, tY))
			{
				y += yMove;
			} else
			{
				y = (tY - 1) * Tile.HEIGHT;// - 1;
			}
		} else if(yMove < 0) // up
		{
			int tY = (int) (y + yMove) / Tile.HEIGHT;
			
			if(!collisionWithTile((int) x / Tile.WIDTH, tY) && 
				!collisionWithTile((int) (x + width) / Tile.WIDTH, tY))
			{
				y += yMove;
			}
		}
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public float getSpeed()
	{
		return speed;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	
	// for testing - TODO: remove and simulate key-presses in tests
	public void setXMove(int speed)
	{
		this.xMove = speed;
	}
	
	public void setYMove(int speed)
	{
		this.yMove = speed;
	}
	
	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getWorld().getTile(x, y).isSolid();
	}
}