package Game.Server;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Game_Data.Game_Model;
import Game.Proxy.Proxy_Manager;

public class Game_ControllerTest extends Game_Controller {
	
	
	public void setUp(){
		gm= new Game_Model(5,0);
		pm = Proxy_Manager.getInstance();
	}
	
	public void tearDown(){
		gm = null;
	}
	
	@Test
	public void testBet() {
		setUp();
		bet(0,100);
		assertEquals(900, gm.getUser(0).getMoney());
		assertEquals(100, gm.getTable().getMoney());
		tearDown();
	}

	@Test
	public void testGiveCard() {
		
	}	

	@Test
	public void testGiveTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTurn() {
		setUp();
		calculateTurn();
		assertEquals(1, gm.getCurUser());
		assertEquals(1, gm.getRound());
		assertEquals(3, gm.getTable().getCardList().size());
		calculateTurn();
		assertEquals(2, gm.getCurUser());
		calculateTurn();
		assertEquals(3, gm.getCurUser());
		calculateTurn();
		assertEquals(4, gm.getCurUser());
		calculateTurn();
		assertEquals(0, gm.getCurUser());
		gm.getUser(1).setState(false);
		calculateTurn();
		assertEquals(2, gm.getCurUser());
		assertEquals(2, gm.getRound());
		gm.getUser(3).setState(false);
		gm.getUser(4).setState(false);
		gm.getUser(0).setState(false);
		calculateTurn();
	}

	@Test
	public void testPassTurn() {
		fail("Not yet implemented");
	}

	@Test
	public void testWinPrize() {
		fail("Not yet implemented");
	}

}
