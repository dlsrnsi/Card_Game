package Game.Game_Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Observer;

public class Table extends Game_Set {

	
	public Table() {
		observerList = new LinkedList();
		cardList = new LinkedList();
		money = 0;
	}

	@Override
	public void setMoney(int money) {
		// TODO Auto-generated method stub
		this.money = money;
		for(int i =0 ; i < observerList.size(); i++){
			observerList.get(i).tableMoneyUpdate(money);
		}
		
	}

	@Override
	public void addCard(int cardNum) {
		cardList.add(cardNum);
		for(int i =0 ; i < observerList.size(); i++){
			observerList.get(i).tableCardUpdate(cardNum);
		}
		
	}
	
}
