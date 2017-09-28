package dev.mygame.tiles;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile
{
	// STATIC STUFF
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile RockTile = new RockTile(2);
	
	public static final int WIDTH = 64;
	public static final int HEIGHT = 64;
	
	// CLASS
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	public int getId()
	{
		return id;
	}
}