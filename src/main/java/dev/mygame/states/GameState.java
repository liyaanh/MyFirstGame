package dev.mygame.states;

import java.awt.Graphics;

import dev.mygame.entities.creatures.Player;
import dev.mygame.gfx.Assets;
import dev.mygame.Handler;
import dev.mygame.worlds.World;
import dev.mygame.display.Camera;

public class GameState extends State
{
	private Player player;
	private World world;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "/maps/World1.json");
		handler.setWorld(world);
		player = new Player(handler, world.getSpawnX(), world.getSpawnY());
	}
	
	@Override
	public void tick()
	{
		player.tick();
		handler.getCamera().setFocus(player.getX() + player.getWidth()/2, player.getY() + player.getHeight()/2);
	}
	
	@Override
	public void render(Graphics g)
	{
		world.render(g);
		player.render(g);
	}
}