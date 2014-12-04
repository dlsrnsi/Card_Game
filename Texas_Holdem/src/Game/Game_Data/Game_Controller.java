package Game.Game_Data;

public class Game_Controller {
	Game_Model gm;
	int numOfUser;

	public Game_Controller() {

	}

	public void startGame(int numOfUser, int numOfJoker, int minimalBet) {
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
		for (int i = 0; i < 3; i++) {
			giveCard(gm.getTable());
		}
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < gm.getUserList().size(); i++) {
				giveCard((User) gm.getUserList().get(i));
				/*System.out.println(((User) gm.getUserList().get(i)).getClass()
						.getName()
						+ i
						+ "은"
						+ ((User) gm.getUserList().get(i)).getCardList()
								.toString() + "을 가지고 있다");*/
			}
		}

	}

	public void bet(int userNum, int money) {
		gm.getTable().setMoney(gm.getTable().getMoney() + money);
		gm.getUserList().get(userNum)
				.setMoney(gm.getUserList().get(userNum).getMoney() - money);
	}

	public void giveCard(Game_Set set) {
		System.out.println(gm.getCardList().size());
		set.cardList.add(gm.getCardList().get(gm.getCardList().size() - 1));
		gm.getCardList().remove(gm.getCardList().size() - 1);
	}

	public void giveTurn(int userNum) {

	}

	public void calculateTurn() {

	}

	public void passTurn(int userNum) {

	}

	public void winPrize() {

	}

}
