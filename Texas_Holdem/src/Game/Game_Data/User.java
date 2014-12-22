package Game.Game_Data;

import java.util.LinkedList;
import java.util.Observer;

public class User extends Game_Set {
	private boolean isAlive;
	private int userID;
	private String userName;

	public User(int userID) {
		observerList=new LinkedList();
		cardList = new LinkedList();
		setUserName(userName);
		this.userID = userID;
	}

	public boolean getState() {
		return isAlive;
	}

	public void setState(boolean state) {
		isAlive = state;
		for(int i = 0 ; i < observerList.size(); i++){
			observerList.get(i).userStateUpdate(userID, state);
		}
	}
	public int getUserID(){
		return userID;
	}
	@Override
	public void setMoney(int money) {
		// TODO Auto-generated method stub
		this.money = money;
		for(int i =0 ; i < observerList.size(); i++){
			observerList.get(i).userMoneyUpdate(userID, money);
		}
		
	}

	@Override
	public void addCard(int cardNum) {
		cardList.add(cardNum);
		observerList.get(userID).userCardUpdate(userID, cardNum);
	}
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
		for(int i = 0 ; i < observerList.size(); i++){
			observerList.get(i).userNameUpdate(userID, userName);
		}
	}
	

}
