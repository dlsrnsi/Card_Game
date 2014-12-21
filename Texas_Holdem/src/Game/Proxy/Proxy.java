package Game.Proxy;

public interface Proxy {
	public void call();
	public void raise(int money);
	public void die();
	public void check();
	public void exitgame();
	public void getTurn();//Run
	public int getUserNum();
	public void setCheck();
}
