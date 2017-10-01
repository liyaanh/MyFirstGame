package dev.mygame.states;

import java.awt.Graphics;

import dev.mygame.entities.creatures.Player;
import dev.mygame.gfx.Assets;
import dev.mygame.Game;
import dev.mygame.worlds.World;
import dev.mygame.display.Camera;

public class GameState extends State
{
	private Player player;
	private World world;
	private int screenWidth;
	private int screenHeight;
	
	public GameState(Game game)
	{
		super(game);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		world = new World(game, "/maps/World1.json");
		player = new Player(game, world.getSpawnX(), world.getSpawnY());
		game.getCamera().setFocus((int)player.getX(), (int)player.getY());
	}
	
	@Override
	public void tick()
	{
		player.tick();
		game.getCamera().setFocus((int)(player.getX() + player.getWidth()/2), (int)(player.getY() + player.getHeight()/2));
	}
	
	@Override
	public void render(Graphics g)
	{
		world.render(g);
		player.render(g);
	}
}