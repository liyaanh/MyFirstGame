package dev.mygame.worlds;

import java.awt.Graphics;
import java.io.FileReader;
import java.util.Arrays;
import java.nio.file.Paths;
import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import dev.mygame.tiles.Tile;
import dev.mygame.utils.Utils;
import dev.mygame.Handler;

public class World
{
	private int width;
	private int height;
	private int spawnX;
	private int spawnY;
	private Handler handler;
	
	private int[][] grid;
	
	public World(Handler handler, String path)
	{
		this.handler = handler;
		loadWorld(path);
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		int tilesRendered = 0;
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				Tile t = getTile(x, y);
				//System.out.println("CamX : " + handler.getCamera().getX() + " CamY : " + handler.getCamera().getY());
				// currently, getTile is returning a default tile piece
				// not the correct tile at id position...
				if(!cullTile(x, y, t.WIDTH, t.HEIGHT))
				{
					t.render(g, (int)(x * t.WIDTH - handler.getCamera().getX()), (int)(y * t.HEIGHT - handler.getCamera().getY()));
					tilesRendered++;
				}
			}
		}
		//System.out.println("Tiles rendered: " + tilesRendered + "/" + grid[0].length * grid.length);
	}
	
	public boolean cullTile(int x, int y, int w, int h)
	{
		// left
		//if((x + 1) * w > handler.getCamera().getX() 
		//	&& (x - 1) * w + w < handler.getCamera().getX() + handler.getCamera().getWidth()
		//	&& (y + 1) * h > handler.getCamera().getY() 
		//	&& (y - 1) * h + h < handler.getCamera().getY() + handler.getCamera().getHeight())
		//{
		if((x + 1) > handler.getCamera().getX() / w
			&& (x) < (handler.getCamera().getX() + handler.getCamera().getWidth()) / w
			&& (y + 1) > handler.getCamera().getY() / h
			&& (y) < (handler.getCamera().getY() + handler.getCamera().getHeight()) / h)
		{
			return false;
		}
		// right
		// up
		// down
		
		
		return true;
	}
	
	public boolean coordinatePrinter(int coord, int camCoord)
	{
		
		return true;
	}
	
	public Tile getTile(int x, int y)
	{
		Tile tile = Tile.tiles[grid[y][x]];
		if(null == tile)
		{
			return Tile.dirtTile;
		}
		return tile;
	}
	
	public int getSpawnX()
	{
		return spawnX;
	}
	
	public int getSpawnY()
	{
		return spawnY;
	}
	
	private void loadWorld(String path)
	{
		JSONArray map = null;

		try
		{
			File jsonFile = Paths.get(World.class.getResource(path).toURI()).toFile();
		
			//System.out.println(jsonFile.getAbsolutePath().toString());
		
			JSONObject jsonObject = (JSONObject) new JSONParser().parse(new FileReader(jsonFile));
			
			// load array of Strings
			map = (JSONArray) jsonObject.get("map");
			this.width = ((Long)jsonObject.get("width")).intValue();
			this.height = ((Long)jsonObject.get("height")).intValue();
			this.spawnX = ((Long)jsonObject.get("spawnX")).intValue();
			this.spawnY = ((Long)jsonObject.get("spawnY")).intValue();
			
		} catch (Exception e)
		{
			System.out.println(e);
		}
		grid = Utils.convertJsonArrayTo2dIntArray(map, width, height);
	}
}