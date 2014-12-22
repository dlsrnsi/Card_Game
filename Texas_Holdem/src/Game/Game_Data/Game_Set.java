package Game.Game_Data;

import java.util.List;
import Game.Proxy.GameObserver;

import Game.Proxy.GameObserver;

public abstract class Game_Set {
	protected List cardList;
	protected int money;
	List<GameObserver> observerList;
	
	public int getMoney() {
		return money;
	}

	abstract public void setMoney(int money);

	public void getCard(int card) {
		cardList.add(card);
	}

	public List getCardList() {
		return cardList;
	}
	
	abstract public void addCard(int cardNum);
	public void registObserver(GameObserver o){
		observerList.add(o);
		System.out.println(this.getClass().getName()+"은 " + observerList.size() + "만큼의 옵저버를 가지고 잇습니다");
	}
}
