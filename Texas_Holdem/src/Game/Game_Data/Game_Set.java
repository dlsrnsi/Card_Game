package Game.Game_Data;

import java.util.List;

public abstract class Game_Set {
	protected List cardList;
	protected int money;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void getCard(int card) {
		cardList.add(card);
	}

	public List getCardList() {
		return cardList;
	}
}
