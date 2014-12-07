package Game.Game_Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Game.Proxy.Dealer_Proxy;
import Game.Proxy.Proxy;
import Game.Proxy.User_Proxy;

public class Game_Controller implements Observer{
	private Game_Model gm;	
	private int numOfUser;
	private List<Thread> playerThreadList;
	private Observable observable;

	public Game_Controller() {
		
		playerThreadList=new LinkedList();
		Thread dealer_Thread=new Thread(new Dealer_Proxy(this, 0));
		playerThreadList.add(dealer_Thread);
		for(int i=0;i<numOfUser;i++){
			Thread user_Thread=new Thread(new User_Proxy(this, i+1));
			playerThreadList.add(user_Thread);
		}
	}

	public void startGame(int numOfUser, int numOfJoker, int minimalBet) {
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
		this.observable=gm;
		observable.addObserver(this);
		for (int i = 0; i < 3; i++) {
			giveCard(gm.getTable());
		}
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < gm.getUserList().size(); i++) {
				giveCard((User) gm.getUserList().get(i));
				/*System.out.println(((User) gm.getUserList().get(i)).getClass()
						.getName()
						+ i
						+ "은"
						+ ((User) gm.getUserList().get(i)).getCardList()
								.toString() + "을 가지고 있다");*/
			}
		}
		System.out.println(gm.getCardList().size());
		giveTurn(0);

	}

	public void bet(int userNum, int money) {
		gm.getTable().setMoney(gm.getTable().getMoney() + money);
		gm.getUserList().get(userNum)
				.setMoney(gm.getUserList().get(userNum).getMoney() - money);
		passTurn(userNum);
	}

	public void giveCard(Game_Set set) {
		set.cardList.add(gm.getCardList().get(gm.getCardList().size() - 1));
		gm.getCardList().remove(gm.getCardList().size() - 1);
	}

	public void giveTurn(int userNum) {
		((Proxy)playerThreadList.get(userNum)).getTurn();
	}

	public void calculateTurn() {

	}

	public void passTurn(int userNum) {

	}

	public void winPrize() {

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
