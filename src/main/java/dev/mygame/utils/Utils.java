package dev.mygame.utils;

import org.json.simple.JSONArray;

public class Utils
{
	public static int[][] convertJsonArrayTo2dIntArray(JSONArray array, int width, int height)
	{
		/* 
		* grid might look like, we access height first, 
		* followed by position in each array (width)
		* 0: XXXXX
		* 1: X   X
		* 2: XXXXX
		*/
		int[][] toReturn = new int[height][width];
		for(int y = 0; y < height; y++)
		{
			String row = (String)array.get(y);
			
			for(int x = 0; x < width; x++)
			{
				toReturn[y][x] = Character.getNumericValue(row.charAt(x));
			}
		}
		
		return toReturn;
	}
}