package dev.mygame.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet
{
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int w, int h)
    {
		// multiply the x and y coordinates by width/height
		// here, so that we don't litter the code doing it
		// in places where this method is used. This allows us
		// to pass in 'blocksize' measurements; meaning we identify
		// grid positions as 0, 1, 2 etc. not 32, 64, 128.
        return sheet.getSubimage(x * w, y * h, w, h);
    }
}