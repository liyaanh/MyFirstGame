package dev.mygame;

import dev.mygame.display.Display;
import dev.mygame.gfx.ImageLoader;
import dev.mygame.gfx.SpriteSheet;
import dev.mygame.gfx.Assets;
import dev.mygame.states.*;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import dev.mygame.input.KeyManager;
import dev.mygame.display.Camera;

public class Game implements Runnable
{
    private Display display;

    public int width;
    public int height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private int x;
    private int y;
	
	private BufferedImage testImage;
	private SpriteSheet testSheet;
	
	private long NANOSECONDS = 1000000000;
	
	private State menuState;
	private State gameState;
	
	private KeyManager keyManager;
	
	private Camera camera;
	
	private Handler handler;

    public Game(String title, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
		keyManager = new KeyManager();
    }

    private void init()
    {
        display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		camera = new Camera(0, 0, width, height);
		handler = new Handler(this);
		
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		State.setState(menuState);
	}

    private void tick()
    {
		keyManager.tick();
		
		if(State.getState() != null)
		{
			// We put all our logic relating to a particular state in
			// its own tick method. That way when we swap states, it
			// seamlessly starts to render the next state.
			State.getState().tick();
		}
    }

    private void render()
    {
        // A bufferstrategy is a way to tell the computer
        // how to draw things to the screen.
        bs = display.getCanvas().getBufferStrategy();
        if(null == bs)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear
        g.clearRect(0, 0, width, height);
        // Draw here.

		if(State.getState() != null)
		{
			// Pass the Graphics object by reference, means we use the
			// same GFX to draw regardless what state we are in.
			State.getState().render(g);
		}

        // End Drawing!
        bs.show();
        g.dispose();
    }

    /*
    * run() is called when we call start()
    */
    public void run()
    {
        init();
		
		int fps = 60;
		double timePerFrame = NANOSECONDS / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		long counter = 0;
		int timeUntilGame = 5; // seconds
		
		while(running)
		{
			now = System.nanoTime();
			long frameTime = now - lastTime;
			delta += frameTime / timePerFrame;
			timer += frameTime;
			final int MAX_DELTA = 1;
			
			//System.out.println("Now: " + now + " Last: " + lastTime);
			
			lastTime = now;
			
			//System.out.println(delta);
			
			if(delta >= MAX_DELTA)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			counter++;
			
			if(timer >= NANOSECONDS)
			{
				timeUntilGame--;
				System.out.println("Ticks and Frames: " + ticks);
				System.out.println("Timer: " + timer);
				ticks = 0;
				timer = 0;
				System.out.println("Countrer :" + counter);

				//break;
			}
			
			if(timeUntilGame <= 0)
			{
				// let's see how this new state renders
				State.setState(gameState);
			}
		}

        // in-case we didn't stop already
        stop();
    }

    public synchronized void start()
    {
        if(running)
        {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();

        
    }

    public synchronized void stop()
    {
        if(!running)
        {
            return;
        }
        running = false;

        try
        {
            thread.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public Camera getCamera()
	{
		return camera;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
