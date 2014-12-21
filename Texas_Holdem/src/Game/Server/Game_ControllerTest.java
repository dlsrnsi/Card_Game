package Game.Server;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.Game_Data.Game_Model;
import Game.Proxy.Proxy_Manager;

public class Game_ControllerTest extends Game_Controller {
	
	
	public void setUp(){
		gm= new Game_Model(5);
		pm = Proxy_Manager.getInstance();
		gm.setCurUser(2);
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
		bet(0,100);
		assertEquals(3, gm.getCurUser());
		assertEquals(0, gm.getRound());
		assertEquals(0, gm.getTable().getCardList().size());
		calculateTurn();
		assertEquals(4, gm.getCurUser());
		calculateTurn();
		assertEquals(0, gm.getCurUser());
		calculateTurn();
		assertEquals(1, gm.getCurUser());
		calculateTurn();
		assertEquals(2, gm.getCurUser());
		assertEquals(3, gm.getTable().getCardList().size());
		die(3);
		calculateTurn();
		assertEquals(4, gm.getCurUser());
		die(0);
		die(1);
		die(2);
		calculateTurn();
		assertEquals(1100, gm.getUser(4).getMoney());
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
