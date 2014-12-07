package Game.Game_Data;

import java.util.LinkedList;

public class User extends Game_Set {
	private boolean isAlive;
	private int userID;

	public User(int userID) {
		cardList = new LinkedList();
		money = 0;
		this.userID = userID;
	}

	public boolean getState() {
		return isAlive;
	}

	public void setState(boolean state) {
		isAlive = state;
	}
	public int getUserID(){
		return userID;
	}
}
