package Game.Game_Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

public class Game_Model extends Observable {
	private List<User> userList;
	private Table table;
	private List cardList;
	private List rankList;
	private int curUser;
	private int round;

	public Game_Model(int numOfUser, int joker) {
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
		round = 0;// Round는 0부터 시작한다;
		table = new Table();
		table.setMoney(0);// 테이블에서는 0원(베팅머니 없음)
		int curUser = 0;// 게임이 시작할때 현재 유저는 0번째 유저이다(딜러)
	}

	public int distinguishWinner() {
		return 0;
	}

	public void calculateCardRank() {
		List cardListForRank;
		List tableCardList = table.getCardList();
		List userCardList;
		for (int i = 0; i < userList.size(); i++) {
			userCardList = userList.get(i).getCardList();
			cardListForRank = new LinkedList();
			cardListForRank.addAll(tableCardList);
			cardListForRank.addAll(userCardList);
			cardListForRank.sort(null);

		}

	}

	public int getCurUser() {
		return curUser;
	}

	public void setCurUser(int i) {
		this.curUser = i;
	}

	public User getUser(int userNum) {
		return (User) userList.get(userNum);
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

	public void notifyObserver() {

	}

	public String getShape(int card) {
		switch (card / 13) {
		case (0):
			return "Spade";
		case (1):
			return "Heart";
		case (2):
			return "Clover";
		case (3):
			return "Diamond";
		case (4):
			return "Joker";
		default:
			return null;
		}
	}

	public String getNumber(int card) {
		switch (card % 13) {
		case (0):
			return "A";
		case (1):
			return "2";
		case (2):
			return "3";
		case (3):
			return "4";
		case (4):
			return "5";
		case (5):
			return "6";
		case (6):
			return "7";
		case (7):
			return "8";
		case (8):
			return "9";
		case (9):
			return "10";
		case (10):
			return "J";
		case (11):
			return "Q";
		case (12):
			return "K";
		default:
			return null;
		}
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}
