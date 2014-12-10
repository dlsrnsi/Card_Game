package Game.Server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
	private Game_Model gm;
	private int numOfUser;
	private Observable observable;

	public static Game_Controller getInstance() {
		if (gc == null) {
			gc = new Game_Controller();
		}
		return gc;
	}

	public void startGame(int numOfUser, int numOfJoker, int minimalBet) {
		pm = Proxy_Manager.getInstance();
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
		this.observable = gm;
		observable.addObserver(this);
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
		bet(1, minimalBet);
		bet(2, minimalBet * 2);
		gm.setCurUser(2);
		calculateTurn();

	}

	public void bet(int userNum, int money) {
		gm.getTable().setMoney(gm.getTable().getMoney() + money);
		gm.getUser(userNum).setMoney(gm.getUser(userNum).getMoney() - money);
		System.out.println("User" + userNum + "이 " + money + "만큼 베팅을 하였습니다");
		passTurn(userNum);
	}

	public void giveCard(Game_Set set) {
		set.getCardList()
				.add(gm.getCardList().get(gm.getCardList().size() - 1));
		gm.getCardList().remove(gm.getCardList().size() - 1);
	}

	public void giveTurn(int userNum) {
		gm.setCurUser(userNum);
		if (gm.getUser(userNum).getState()) {
			System.out.println("유저"+userNum+"의 턴입니다");
			(pm.getProxy(userNum)).getTurn();
		}
		calculateTurn();
	}

	public void calculateTurn() {
		System.out.println("calTurn");
		int curUser = gm.getCurUser();
		curUser++;
		if (curUser == numOfUser) {
			giveTurn(0);
		} else if (curUser == 1) {
			if (gm.getRound() == 3) {
				winPrize(gm.distinguishWinner());
			} else if (gm.getRound() == 0) {
				gm.setRound(gm.getRound() + 1);
				for (int j = 0; j < 3; j++) {
					giveCard(gm.getTable());
				}
				giveTurn(curUser);
			} else {
				gm.setRound(gm.getRound() + 1);
				giveCard(gm.getTable());
				giveTurn(curUser);
			}
		} else {
			giveTurn(curUser);
		}

	}

	public void passTurn(int userNum) {
		

	}

	public void winPrize(int winner) {
		int money = gm.getTable().getMoney();
		gm.getTable().setMoney(0);
		gm.getUser(winner).setMoney(gm.getUser(winner).getMoney() + money);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
