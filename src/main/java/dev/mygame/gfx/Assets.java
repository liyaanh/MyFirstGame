package dev.mygame.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	private static final int WIDTH = 32, HEIGHT = 32;
	
	public static BufferedImage playerOne, playerTwo, tree, dirt, rock, grassOne, grassTwo;
	
	public static void init()
	{
		// because SS and ILoader are in same package, we don't need imports.
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
	
		playerOne = sheet.crop(0, 0, WIDTH, HEIGHT);
		playerTwo = sheet.crop(1, 0, WIDTH, HEIGHT);
		dirt = sheet.crop(0, 3, WIDTH, HEIGHT);
		tree = sheet.crop(1, 3, WIDTH, HEIGHT);
		grassOne = sheet.crop(2, 3, WIDTH, HEIGHT);
		rock = sheet.crop(3, 2, WIDTH, HEIGHT);
		grassTwo = sheet.crop(3, 3, WIDTH, HEIGHT);
	}
}