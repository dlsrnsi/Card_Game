package Game.Proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.ReentrantLock;

import Client.ReceiverThread;
import Game.Game_Data.Game_Model;
import Game.Game_Data.Table;
import Game.Server.Game_Controller;

public class Dealer_Proxy extends Thread implements Proxy, GameObserver {
	Game_Controller gc;
	Proxy_Manager pm;
	Socket socket;

	PrintWriter writer;
	int userNum;
	int minimalBet;
	boolean check;
	boolean isTurn;
	String userName;

	public Dealer_Proxy(Socket socket, int userNum) {
		gc = Game_Controller.getInstance();
		pm = Proxy_Manager.getInstance();
		minimalBet=10;
		this.socket = socket;
		this.userNum = userNum;
		isTurn = false;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			pm.getList().add(writer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			userName = reader.readLine();
			pm.setCount(pm.getCount() + 1);
			sendAll("#" + userName + " is Joined/" + pm.getCount());

			while (true) {
				if (pm.getProxy(userNum).getUserNum()==userNum) {
					String string = reader.readLine();
					String str = string.split("/")[0];
					System.out.println("str은" + str);
					if (str == null) {
						break;
					} else if (str.equals("StartGame")) {
						System.out.println(str);
						startGame();
					} else if (str.equals("Call")) {
						call();
					} else if (str.equals("Raise")) {
						String str2 = string.split("/")[1];
						raise(Integer.parseInt(str2));
					} else if (str.equals("Die")) {
						die();
					} else if (str.equals("Check")) {
						check();
					} else if (str.equals("ChangeOption")) {
						String str2 = string.split("/")[1];
						changeOption(Integer.parseInt(str2));
					}

					sendAll(userName + ">" + str);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			pm.getList().remove(writer);
			sendAll("#" + userName + " is out/");
			pm.setCount(pm.getCount() - 1);
			exitgame();
			try {
				socket.close();
			} catch (Exception ignored) {
			}
		}
	}

	private void sendAll(String str) {
		for (PrintWriter writer : pm.getList()) {
			writer.println(str);
			writer.flush();
		}
	}

	public void startGame() {
		if (!gc.checkStarted()) {
			gc.startGame(pm.getCount(), minimalBet);
		}
	}

	public void changeOption(int minimalBet) {
		if (!gc.checkStarted()) {
			this.minimalBet = minimalBet;
		}
	}

	@Override
	public void call() {
		if (isTurn) {
			gc.bet(userNum, gc.getMinimalBet());
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
	}

	@Override
	public void raise(int money) {
		if (isTurn) {
			gc.setMinimalBet(gc.getMinimalBet() + money);
			gc.bet(userNum, gc.getMinimalBet());
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}

	}

	@Override
	public void die() {
		if (isTurn) {
			gc.die(userNum);
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}

	}

	@Override
	public void check() {
		if (isTurn) {
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
	}

	@Override
	public void exitgame() {
		gc.die(userNum);
		this.isTurn = false;
		this.check = false;
	}

	@Override
	public void getTurn() {
		System.out.println("행동을 입력하세요");
		System.out.println("Proxy"+userNum+"이 턴을 얻었습니다");
		String str = userName + "의 턴입니다";
		sendAll(str);
		this.isTurn = true;
	}

	@Override
	public int getUserNum() {
		// TODO Auto-generated method stub
		return userNum;
	}

	public void setCheck() {
		this.check = true;
	}

	@Override
	public void userCardUpdate(int userNum, int card) {
		String str = "userCard/" + String.valueOf(userNum) + "/"
				+ String.valueOf(card)+"/" + userName;
		sendAll(str);
	}

	@Override
	public void userStateUpdate(int userNum, boolean state) {
		String str = "userState/" + String.valueOf(userNum) + "/"
				+ String.valueOf(state);
		sendAll(str);
	}

	@Override
	public void userMoneyUpdate(int userNum, int money) {
		String str = "userMoney/" + String.valueOf(userNum) + "/"
				+ String.valueOf(money);
		sendAll(str);
	}

	@Override
	public void tableCardUpdate(int card) {
		String str = "tableCard/" + String.valueOf(card);
		sendAll(str);
	}

	@Override
	public void tableMoneyUpdate(int money) {
		String str = "tableMoney/" + String.valueOf(money);
		sendAll(str);
	}

	@Override
	public void userNameUpdate(int userNum, String userName) {
		String str = "userName/" + String.valueOf(userNum) + "/" + userName;
		sendAll(str);
	}

	@Override
	public String getUserName() {
		return userName;

	}
}