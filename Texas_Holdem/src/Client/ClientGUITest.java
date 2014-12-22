package Client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClientGUITest extends ClientGUI{
	SenderThread st=new SenderThread(null, null);
	@Before
	public void setUp() throws Exception {
		Myframe(st);
	}

	@Test
	public void testSetframe() throws Exception {
		setUp();
		String str = "#p1 is joined";
		setframe(str);
		assertEquals("#p1",PlayerName1.getText());
		
	}

	@Test
	public void testSetframeUserCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetframeUserState() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetframeUserMoney() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetframetableCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetframetableMoney() {
		fail("Not yet implemented");
	}

	@Test
	public void testMyframe() {
		fail("Not yet implemented");
	}

}
