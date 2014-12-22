package Game.Proxy;

public interface GameObserver {
	public void userCardUpdate(int userNum, int card);
	public void userStateUpdate(int userNum, boolean state);
	public void userMoneyUpdate(int userNum, int money);
	public void tableCardUpdate(int card);
	public void tableMoneyUpdate(int money);
	public void userNameUpdate(int userNum, String userName);

}
