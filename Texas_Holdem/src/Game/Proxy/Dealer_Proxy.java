package Game.Proxy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Game.Game_Data.*;

public class Dealer_Proxy implements Proxy,Runnable {
	int numOfJoker = 0;
	int minimalBet = 1;
	int userID;
	Lock lock;
	public Dealer_Proxy(int userID){
		this.userID = userID;
		lock = new ReentrantLock();
	}

	public void startGame() {
	}

	public void changeOption(int numOfJoker, int minimalBet) {
		this.numOfJoker = numOfJoker;
		this.minimalBet = minimalBet;
	}

	@Override
	public void call() {
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
	public void raise(int money) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
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
	public void getTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getUserID() {
		// TODO Auto-generated method stub
		return userID;
	}

}
