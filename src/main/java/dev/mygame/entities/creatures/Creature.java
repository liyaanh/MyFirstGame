package dev.mygame.entities.creatures;

import dev.mygame.entities.Entity;
import dev.mygame.Handler;

public abstract class Creature extends Entity
{
	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 1.0f;
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
		x += xMove;
		y += yMove;
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
}