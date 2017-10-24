package dev.mygame.entities.creatures;

import java.awt.Graphics;

import dev.mygame.worlds.World;
import dev.mygame.tiles.Tile;
import dev.mygame.gfx.Assets;
import dev.mygame.Handler;
import dev.mygame.entities.shapes.Rectangle;
import dev.mygame.utils.CollisionUtils;

public class Player extends Creature
{
	private Rectangle colRect;
	
	public Player(Handler handler, float x, float y)
	{
		super(handler, x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT);
		colRect = new Rectangle(handler, x, y, 32, 32);
	}
	
	@Override
	public void tick()
	{
		getInput();
		//checkCollisionWithWorld2();
		move();
		//checkCollisionWithWorld();
		//colRect.update(x + width / 2 , y + 48);
		//System.out.println(colRect.getX() + " " + colRect.getY());
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
			//boolean hasCollidedLeft = handler.getWorld().getTile((int) (x + xMove) / 64, (int) y / 64).isSolid();
			//if(xMove < 0 && hasCollidedLeft)
			//{
			//	// currently, a collision occurs when the character goes left
			//	// once that collision occurs, xMove becomes equal to 0
			//	// the else statement then gets triggered after every go because;
			//	// a) xMove stops being less than 0, hasCollidedLeft doesn't even get checked
			//	// b) we set xMove to 0 before this method even occurs, so it will never
			//	// 		trigger the collision code in the first place
			//  // c) we need to getInput, which sets speed, and then xMove ONLY IF
			//  //		a collision with world hasn't occured, move gets called with 0 speed
			//	xMove = 0;
			//	System.out.println("Collision");
			//}
			//else
			//{
				xMove = -speed;
			//}
		}
		if(handler.getKeyManager().right)
		{
			xMove = +speed;
		}
	}
	
	public void checkCollisionWithWorld()
	{
		int txLeft = (int) (x + xMove) / 64;
		int txRight = (int) (x + xMove + width) / 64;
		int tyUp = (int) (y + yMove) / 64;
		int tyDown = (int) (y + yMove + height) / 64;
		boolean hasCollidedLeft = handler.getWorld().getTile(txLeft, (int) y / 64).isSolid();
		boolean hasCollidedRight = handler.getWorld().getTile(txRight, (int) y / 64).isSolid();
		boolean hasCollidedUp = handler.getWorld().getTile((int) x / 64, tyUp).isSolid();
		boolean hasCollidedDown = handler.getWorld().getTile((int) x / 64, tyDown).isSolid();
		
		System.out.println("X in map: " + (int)(x / 64));
		System.out.println("Y in map: " + (int)(y / 64));

		//System.out.println(hasCollidedLeft);
		// check for left
		// map X and Y is measured in single digit integers ranging up to the number of tiles in the X direction (curr: 10)
		// we get the current player position in terms of this value by dividing player X by TileWidth
		// e.g. player X = 65, map coordinate block 2 = 1, blocksize is 64. 1 / 64 integer division produces 1, therefore,
		// player is in line with the right edge of block 1
		// if the left side of the player goes < the right side of the edge of a SOLID tile, we make xMove = 0
		
			int newX = (int) x / 64;
		// this doesn't work how I think it does, if colliding left, AND then up, the boolean operator short-circuits
		// and never checks the down. So the block below, even if it is solid, is not checked for collision as long as
		// one is above. The player slides through.
		if(xMove < 0 && (hasCollidedUp || hasCollidedDown)&& hasCollidedLeft )
		{
			xMove = 0;
			System.out.println("Collision");
			// pop the player to the edge of the tile, so we don't overlap with it
			// TODO: bouncing between the wall and its reset position
			//x = newX + handler.getWorld().getTile(newX, (int) y / 64).WIDTH;
		}
		else if(xMove > 0 && (hasCollidedUp || hasCollidedDown) && hasCollidedRight)
		{
			xMove = 0;
			System.out.println("Collision");
			// pop the player to the edge of the tile, so we don't overlap with it
			// TODO: putting us at the wrong tile
			//x = newX + handler.getWorld().getTile(((int) (x + width) / 64) - 1, (int) y / 64).WIDTH;
		}
		
		if(yMove < 0 && (hasCollidedLeft || hasCollidedRight) && hasCollidedUp)
		{
			yMove = 0;
			System.out.println("Collision");
			// pop the player to the edge of the tile, so we don't overlap with it
			// TODO: bouncing between the wall and its reset position
			//x = newX + handler.getWorld().getTile(newX, (int) y / 64).WIDTH;
		}
		else if(yMove > 0 && (hasCollidedLeft || hasCollidedRight) && hasCollidedDown)
		{
			yMove = 0;
			System.out.println("Collision");
			// pop the player to the edge of the tile, so we don't overlap with it
			// TODO: putting us at the wrong tile
			//x = newX + handler.getWorld().getTile(((int) (x + width) / 64) - 1, (int) y / 64).WIDTH;
		}
	}
	
	public void checkCollisionWithWorld2()
	{
		if(xMove > 0)
		{
			int tX = (int) (x + xMove + width) / Tile.WIDTH;
			if(collisionWithTile(tX, (int) y / Tile.HEIGHT)
				|| // Short-circuit OR required because my player is the same size as the tiles
				collisionWithTile(tX, (int) (y + height) / Tile.HEIGHT))
			{
				// because they are the same size, it means that if checking with AND will not work
				// on single blocks that collidable but not surrounded by others. This is because
				//, unless we line up perfectly, or have a mid-point coordinate, both coordinates won't
				// be touching+colliding with the block. Therefore, the player slides through.
				xMove = 0;
				x = (tX -1)* Tile.WIDTH - 1;
			}
		} else if(xMove < 0)
		{
			int tX = (int) (x + xMove) / Tile.WIDTH;
			if(collisionWithTile(tX, (int) y / Tile.HEIGHT)
				||
				collisionWithTile(tX, (int) (y + height) / Tile.HEIGHT))
			{
				xMove = 0;
				// if we collide on the Y, this collision is true and will shoot us to the other side of the map
				x = (tX +1) * Tile.WIDTH;
			}
		}
		
		if(yMove > 0)
		{
			int tY = (int) (y + yMove + height) / Tile.HEIGHT;
			if(collisionWithTile((int) x / Tile.WIDTH, tY)
				||
				collisionWithTile((int) (x + width) / Tile.WIDTH, tY))
			{
				yMove = 0;
				y = (tY - 1) * Tile.HEIGHT - 1;
			}
		} else if(yMove < 0)
		{
			int tY = (int) (y + yMove) / Tile.HEIGHT;
			if(collisionWithTile((int) (x + width) / Tile.WIDTH, tY)
				||
				collisionWithTile((int) x / Tile.WIDTH, tY))
			{
				yMove = 0;
				y = (tY + 1) * Tile.HEIGHT;
			}
		}
	}
	
	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.playerOne, (int)(x - handler.getCamera().getX()), (int)(y - handler.getCamera().getY()), width, height, null);
		colRect.render(g);
	}
}