package Game.Game_Data;

public class Game_Controller {
	Game_Model gm;
	int numOfUser;

	public Game_Controller() {

	}

	public void startGame(int numOfJoker, int minimalBet) {
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
	}

}
