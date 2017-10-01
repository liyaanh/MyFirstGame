package dev.mygame.states;

import java.awt.Graphics;

import dev.mygame.entities.creatures.Player;
import dev.mygame.gfx.Assets;
import dev.mygame.Game;
import dev.mygame.worlds.World;

public class GameState extends State
{
	private Player player;
	private World world;
	
	public GameState(Game game)
	{
		super(game);
		world = new World("/maps/World1.json");
		player = new Player(game, world.getSpawnX(), world.getSpawnY());
	}
	
	@Override
	public void tick()
	{
		player.tick();
	}
	
	@Override
	public void render(Graphics g)
	{
		world.render(g);
		player.render(g);
	}
}