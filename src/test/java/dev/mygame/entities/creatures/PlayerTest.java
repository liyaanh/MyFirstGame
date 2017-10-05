import org.junit.*;

import dev.mygame.Game;
import dev.mygame.Handler;
import dev.mygame.entities.creatures.Player;

public class PlayerTest
{
	Player p;
	
	@Before
	public void setup()
	{
		p = new Player(new Handler(new Game("", 0, 0)), 0, 0);
	}
	
	@Test
	public void testMove()
	{
		p.setXMove(3);
		p.setYMove(3);
		
		p.move();
		
		Assert.assertEquals(3.0f, p.getX(), 1.0f);
		Assert.assertEquals(3.0f, p.getY(), 1.0f);
	}
	
	@Test
	public void testMove2()
	{
		p.setXMove(6);
		p.setYMove(9);
		
		p.move();
		
		Assert.assertEquals(6.0f, p.getX(), 1.0f);
		Assert.assertEquals(9.0f, p.getY(), 1.0f);
	}
}