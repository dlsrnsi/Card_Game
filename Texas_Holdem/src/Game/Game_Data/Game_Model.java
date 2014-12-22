package Game.Game_Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import Game.Proxy.Proxy_Manager;

public class Game_Model {
	private List<User> userList;
	private Table table;
	private List cardList;
	private int curUser;
	private int round;
	List cardListForRank;
	

	public Game_Model(int numOfUser) {
		userList = new LinkedList();
		for (int i = 0; i < numOfUser; i++) {
			User user = new User(i);
			System.out.println("유저" + i + "가 생성되었습니다");
			userList.add(user);
		}
		Random r = new Random();
		int ran = 0;
		boolean check;
		cardList = new LinkedList<>();
		for (int i = 0; i < 52 ; i++) {
			ran = r.nextInt(52) + 1;
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
		int tempMax = 0;
		int tempMaxUser=0;
		for(int i = 0 ; i < userList.size() ; i ++){
			User user = getUser(i);
			if(user.getState()){
				int userRank = calculateCardRank(i);
				if(userRank>tempMax){
					tempMax=userRank;
					tempMaxUser = i;
				}
			}
		}
		
		return tempMaxUser;
	}

	public int calculateCardRank(int userNum) {
		cardListForRank = new LinkedList();
		cardListForRank.addAll(table.getCardList());
		cardListForRank.addAll(getUser(userNum).getCardList());
		int [] rank = new int[7];
		for(int j = 0; j < cardListForRank.size() ; j++){
			rank[j] = (int) cardListForRank.get(j);
		}
		return HandEvaluator.defineHand(rank);
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

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

}
