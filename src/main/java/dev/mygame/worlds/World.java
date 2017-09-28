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

public class World
{
	private int width;
	private int height;
	
	private int[][] grid;
	
	public World(String path)
	{
		loadWorld(path);
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				Tile t = getTile(x, y);
				// currently, getTile is returning a default tile piece
				// not the correct tile at id position...
				t.render(g, x * t.WIDTH, y * t.HEIGHT);
			}
		}
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
	
	private void loadWorld(String path)
	{
		JSONParser parser = new JSONParser();
		JSONArray map = null;

		try
		{
			File jsonFile = Paths.get(World.class.getResource(path).toURI()).toFile();
		
			System.out.println(jsonFile.getAbsolutePath().toString());
			Object obj = parser.parse(new FileReader(jsonFile));
			
			System.out.println(null == obj);
		
			JSONObject jsonObject = (JSONObject) obj;
			
			// load array of Strings
			map = (JSONArray) jsonObject.get("map");
			this.width = ((Long)jsonObject.get("width")).intValue();
			this.height = ((Long)jsonObject.get("height")).intValue();
			/*
			for(Object mapElement : map)
			{
				System.out.println((String) mapElement);
			}*/
		} catch (Exception e)
		{
			System.out.println(e);
		}
		
		// TODO: follow up from 2017.09.23 - map is now loaded
		// TODO: move on to next Java Game tutorial
		// Next is to convert these arrays to a 2d array of integers
		
		//System.out.println(Arrays.toString(map));
		
		//this.width = 5;
		//this.height = 5;
		//grid = new int[height][width];
		/* 
		* grid might look like, we access height first, 
		* followed by position in each array (width)
		* 0: XXXXX
		* 1: X   X
		* 2: XXXXX
		*/

		/*
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				grid[y][x] = 0;
			}
		}
		*/
		grid = convertJsonArrayTo2dIntegerMap(map);
	}
	
	private int[][] convertJsonArrayTo2dIntegerMap(JSONArray mapIn)
	{
		// call length method on String, and size method
		// on JSONArray (child of ArrayList)
		//this.width = ((String)mapIn.get(0)).length();
		//this.height = mapIn.size();
		
		int[][] toReturn = new int[height][width];
		
		System.out.println("convertJsonArrayTo2dIntegerMap()");
		for(int y = 0; y < height; y++)
		{
			String row = (String)mapIn.get(y);
			
			for(int x = 0; x < width; x++)
			{
				char tileId = row.charAt(x);
				System.out.print(tileId);
				// char needs to be converted to int
				toReturn[y][x] = Character.getNumericValue(row.charAt(x));
			}
			System.out.println();
		}
		
		//System.out.println(Arrays.deepToString(toReturn));
		
		return toReturn;
	}
}