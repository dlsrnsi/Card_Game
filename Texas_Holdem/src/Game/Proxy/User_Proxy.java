package Game.Proxy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Game.Game_Data.Game_Controller;

public class User_Proxy implements Proxy, Runnable {
	int userID;
	Lock lock;

	public User_Proxy(Game_Controller gc, int userID) {
		this.userID = userID;
		lock = new ReentrantLock();
	}

	@Override
	public void call() {
		// TODO Auto-generated method stub

	}

	@Override
	public void raise(int money) {
		// TODO Auto-generated method stub

	}

	@Override
	public void die() {
		// TODO Auto-generated method stub

	}

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitgame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getTurn();
	}

	@Override
	public void getTurn() {
		// TODO Auto-generated method stub
		lock.lock();
		try{
			
		}
		catch(Exception e){
			
		}
		finally{
			lock.unlock();
		}

	}

	@Override
	public int getUserID() {
		// TODO Auto-generated method stub
		return userID;
	}

}
