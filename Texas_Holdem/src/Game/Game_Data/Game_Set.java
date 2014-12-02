package Game.Game_Data;

import java.util.List;

public abstract class Game_Set {
	private List cardList;
	private int money;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public void getCard(int card){
		cardList.add(card);
	}
	public void operateMoney(int bettingMoney){
		this.money=this.money+bettingMoney;
	}

}
