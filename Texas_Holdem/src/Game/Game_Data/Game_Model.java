package Game.Game_Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game_Model {
	private List<User> userList;
	private List<Thread> userThreadList;
	private Table table;
	private List cardList;
	private int curUser;
	
	public Game_Model(int numOfUser, int joker, int minimalBet) {
		userList = new LinkedList();
		userThreadList = new LinkedList();
		for (int i = 0; i < numOfUser; i++) {
			User user = new User();
			user.setMoney(1000);//������ 1000���� ������ �����Ѵ�
			System.out.println("����"+i+"�� �����Ǿ����ϴ�");
			userList.add(user);
			Thread userThread=new Thread(user);
			userThreadList.add(userThread);
			userThread.start();
			System.out.println("����"+i+"�� �����Ǿ����ϴ�");
		}
		cardList = new LinkedList<>();
		for (int i = 0; i < 52 + joker; i++) {
			cardList.add(i);
		}
		Table table = new Table();
		table.setMoney(0);//���̺����� 0��(���øӴ� ����)
		int curUser=0;//������ �����Ҷ� ���� ������ 0��° �����̴�(����)
	}
	public User distinguishWinner(){
		User winner = new User();//���߿� �ٲ߽ô�
		return winner;
	}
	public void calculateCardRank(){
		
	}
	public User getCurUser(){
		return (User)userList.get(curUser);
	}
	public List<User> getUserList() {
		return userList;
	}

	public List getCardList() {
		return cardList;
	}

	public Table getTable() {
		return table;
	}

}
