package Game.Game_Data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Game.Proxy.Dealer_Proxy;
import Game.Proxy.Proxy;
import Game.Proxy.User_Proxy;

public class Game_Controller implements Observer {
	private static Game_Controller gc;
	private Game_Model gm;
	private int numOfUser;
	private Observable observable;

	public static Game_Controller getInstance(){
		if(gc==null){
			gc = new Game_Controller();
		}
		return gc;
	}

	public void startGame(int numOfUser, int numOfJoker, int minimalBet) {
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
		this.observable = gm;
		observable.addObserver(this);
		for (int i = 0; i < 3; i++) {
			giveCard(gm.getTable());
		}
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
		System.out.println(gm.getCardList().size());
		giveTurn(1);

	}

	public void bet(int userNum, int money) {
		gm.getTable().setMoney(gm.getTable().getMoney() + money);
		gm.getUser(userNum).setMoney(gm.getUser(userNum).getMoney() - money);
		passTurn(userNum);
	}

	public void giveCard(Game_Set set) {
		set.cardList.add(gm.getCardList().get(gm.getCardList().size() - 1));
		gm.getCardList().remove(gm.getCardList().size() - 1);
	}

	public void giveTurn(int userNum) {
		gm.setCurUser(userNum);

	}

	public void calculateTurn() {
		try{
			giveTurn(gm.findNextUser(gm.getCurUser()));
		}
		catch(NullPointerException e){
			winPrize(gm.getCurUser());
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
