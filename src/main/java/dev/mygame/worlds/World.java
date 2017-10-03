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
import dev.mygame.Game;

public class World
{
	private int width;
	private int height;
	private int spawnX;
	private int spawnY;
	private Game game;
	
	private int[][] grid;
	
	public World(Game game, String path)
	{
		this.game = game;
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
				//System.out.println("CamX : " + game.getCamera().getX() + " CamY : " + game.getCamera().getY());
				// currently, getTile is returning a default tile piece
				// not the correct tile at id position...
				if(renderHelper(x, y, t.WIDTH, t.HEIGHT))
				{
					t.render(g, x * t.WIDTH - game.getCamera().getX(), y * t.HEIGHT - game.getCamera().getY());
					tilesRendered++;
				}
			}
		}
		System.out.println("Tiles rendered: " + tilesRendered + "/" + grid[0].length * grid.length);
	}
	
	public boolean renderHelper(int x, int y, int w, int h)
	{
		// left
		if((x + 1) * w > game.getCamera().getX() 
			&& (x - 1) * w + w < game.getCamera().getX() + game.getCamera().getWidth()
			&& (y + 1) * h > game.getCamera().getY() 
			&& (y - 1) * h + h < game.getCamera().getY() + game.getCamera().getHeight())
		{
			return true;
		}
		// right
		// up
		// down
		
		
		return false;
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