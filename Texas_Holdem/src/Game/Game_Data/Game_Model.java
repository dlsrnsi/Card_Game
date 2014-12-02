package Game.Game_Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game_Model {
	private List<Player> userList;
	private List cardList;
	private Table table;

	public Game_Model(int numOfUser, int joker, int minimalBet) {
		userList = new LinkedList();
		for (int i = 0; i < numOfUser; i++) {
			Player user = new Player();
			user.setMoney(1000);
			userList.add(user);
		}
		cardList = new LinkedList<>();
		for(int i= 0; i< 52+joker;i++){
			cardList.add(i);
		}
		Table table=new Table();
		table.setMoney(0);
		
	}

}
