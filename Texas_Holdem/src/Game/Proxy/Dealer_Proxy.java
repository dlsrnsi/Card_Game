package Game.Proxy;

import Game.Game_Data.*;

public class Dealer_Proxy implements Proxy {
	int numOfJoker = 0;
	int minimalBet = 1;

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

}
