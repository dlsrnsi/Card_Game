package Game.Server;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import Game.Game_Data.Game_Model;
import Game.Game_Data.Game_Set;
import Game.Game_Data.User;
import Game.Proxy.Dealer_Proxy;
import Game.Proxy.Proxy;
import Game.Proxy.Proxy_Manager;
import Game.Proxy.User_Proxy;

public class Game_Controller implements Observer {
	private static Game_Controller gc;
	Proxy_Manager pm;
	Game_Model gm;
	private int minimalBet;
	private boolean check;
	private boolean isStarted;

	public static Game_Controller getInstance() {
		if (gc == null) {
			gc = new Game_Controller();
		}
		return gc;
	}

	public void startGame(int numOfUser, int minimalBet) {
		this.check = false;
		this.isStarted = true;
		pm = Proxy_Manager.getInstance();
		gm = new Game_Model(numOfUser);
		this.minimalBet = minimalBet;
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < numOfUser; i++) {
				giveCard((User) gm.getUser(i));

				System.out.println(((User) gm.getUserList().get(i)).getClass()
						.getName()
						+ i
						+ "은"
						+ ((User) gm.getUserList().get(i)).getCardList()
								.toString() + "을 가지고 있다");

			}
		}
		gm.addObserver(this);
		bet(1, minimalBet);
		bet(2, minimalBet * 2);
		gm.setCurUser(1);
		calculateTurn();

	}

	public void bet(int userNum, int money) {
		gm.getTable().setMoney(gm.getTable().getMoney() + money);
		gm.getUser(userNum).setMoney(gm.getUser(userNum).getMoney() - money);
		System.out.println("User" + userNum + "이 " + money + "만큼 베팅을 하였습니다");
	}

	public void giveCard(Game_Set set) {
		set.getCardList()
				.add(gm.getCardList().get(gm.getCardList().size() - 1));
		gm.getCardList().remove(gm.getCardList().size() - 1);
	}

	public void giveTurn(int userNum) {
		gm.setCurUser(userNum);
		if (gm.getUser(userNum).getState()) {
			System.out.println("유저" + userNum + "의 턴입니다");
			if(check){
				//(pm.getProxy(userNum)).setCheck();
				this.check = false;
			}
			//(pm.getProxy(userNum)).getTurn();
		} else {
			calculateTurn();
		}
	}

	public void die(int userNum) {
		gm.getUser(userNum).setState(false);
	}

	public void calculateTurn() {
		int livingUser = 0;
		int maybeWinner = 0;
		for(int i = 0 ; i < gm.getUserList().size(); i++){
			if(gm.getUser(i).getState()){
				livingUser++;
				maybeWinner = i;
			}
		}
		if(livingUser==1){
			winPrize(maybeWinner);
			System.out.println(maybeWinner+"가 이겼습니다");
		}
		else{
			System.out.println("calTurn");
			int curUser = gm.getCurUser();
			curUser++;
			if (curUser == gm.getUserList().size()) {
				giveTurn(0);
			} else if (curUser == 2) {
				if (gm.getRound() == 3) {
					winPrize(gm.distinguishWinner());
				} else if (gm.getRound() == 0) {
					gm.setRound(gm.getRound() + 1);
					for (int j = 0; j < 3; j++) {
						giveCard(gm.getTable());
					}
					this.check = true;
					giveTurn(curUser);
				} else {
					gm.setRound(gm.getRound() + 1);
					giveCard(gm.getTable());
					giveTurn(curUser);
					this.check = true;
				}
			} else {
				giveTurn(curUser);
			}
		}
	}

	public void winPrize(int winner) {
		int money = gm.getTable().getMoney();
		gm.getTable().setMoney(0);
		gm.getUser(winner).setMoney(gm.getUser(winner).getMoney() + money);
	}

	public int getMinimalBet() {
		return minimalBet;
	}

	public void setMinimalBet(int minimalBet) {
		this.minimalBet = minimalBet;
	}
	public boolean checkStarted(){
		return isStarted;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
