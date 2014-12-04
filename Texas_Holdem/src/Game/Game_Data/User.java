package Game.Game_Data;

import java.util.LinkedList;

public class User extends Game_Set {
	private boolean isAlive;

	public User() {
		cardList = new LinkedList();
		money=0;
	}

	public boolean getState() {
		return isAlive;
	}

	public void setState(boolean state) {
		isAlive = state;
	}

}
