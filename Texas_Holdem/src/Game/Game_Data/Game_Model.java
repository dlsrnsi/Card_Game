package Game.Game_Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game_Model extends Observable{
	private List<User> userList;
	private Table table;
	private List cardList;
	private int curUser;

	public Game_Model(int numOfUser, int joker, int minimalBet) {
		userList = new LinkedList();
		for (int i = 0; i < numOfUser; i++) {
			User user = new User(i);
			user.setMoney(1000);// 유저는 1000원을 가지고 시작한다
			System.out.println("유저" + i + "가 생성되었습니다");
			userList.add(user);
		}
		Random r = new Random();
		int ran = 0;
		boolean check;
		cardList = new LinkedList<>();
		for (int i = 0; i < 52 + joker; i++) {
			ran = r.nextInt(52 + joker) + 1;
			check = true;
			for (int j = 0; j < i; j++) {
				if ((int) cardList.get(j) == ran) {
					i--;
					check = false;
				}
			}
			if (check) {
				cardList.add(ran);
			}
		}
		table = new Table();
		table.setMoney(0);// 테이블에서는 0원(베팅머니 없음)
		int curUser = 0;// 게임이 시작할때 현재 유저는 0번째 유저이다(딜러)
	}

	public User distinguishWinner() {
		User winner = new User(0);// 나중에 바꿉시다
		return winner;
	}

	public void calculateCardRank() {

	}

	public User getCurUser() {
		return (User) userList.get(curUser);
	}

	public void setCurUser(int i) {
		this.curUser = i;
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
	
	public void notifyObserver(){
		
	}

}
