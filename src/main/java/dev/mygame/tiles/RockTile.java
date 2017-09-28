package dev.mygame.tiles;

import dev.mygame.gfx.Assets;

public class RockTile extends Tile
{
	public RockTile(int id)
	{
		super(Assets.rock, id);
	}
	
	@Override
	public boolean isSolid()
	{
		return true;
	}
}