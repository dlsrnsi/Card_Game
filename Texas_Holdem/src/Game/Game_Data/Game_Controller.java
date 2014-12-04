package Game.Game_Data;

public class Game_Controller {
	Game_Model gm;
	int numOfUser;

	public Game_Controller() {

	}

	public void startGame(int numOfUser,int numOfJoker, int minimalBet) {
		gm = new Game_Model(numOfUser, numOfJoker, minimalBet);
		
	}
	public void bet(int userNum, int money){
		gm.getTable().setMoney(gm.getTable().getMoney()+money);
		gm.getUserList().get(userNum).setMoney(gm.getUserList().get(userNum).getMoney()-money);
	}
	public void dropCard(){
		int card=(int)gm.getCardList().get(gm.getTable().getCardList().size()-1);
		gm.getTable().getCardList().add(card);
	}
	public void giveTurn(int userNum){
		
	}
	public void endTurn(int userNum){
		
	}
	public void calculateTurn(){
		
	}
	public void passTurn(int userNum){
		
	}
	public void winPrize(){
		
	}

}
