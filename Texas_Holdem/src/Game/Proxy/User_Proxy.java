package Game.Proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

import Game.Server.Game_Controller;

public class User_Proxy extends Thread implements Proxy {
	Game_Controller gc;
	Proxy_Manager pm;
	Socket socket;
	PrintWriter writer;
	private int userNum;
	boolean check;
	boolean isTurn;

	public User_Proxy(Socket socket, int userNum) {
		gc = Game_Controller.getInstance();
		this.socket = socket;
		this.userNum = userNum;
		pm = Proxy_Manager.getInstance();

		try {
			writer = new PrintWriter(socket.getOutputStream());
			pm.getList().add(writer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void run() {
		String name = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			name = reader.readLine();
			pm.setCount(pm.getCount() + 1);
			sendAll("#" + name + " is Joined" + pm.getCount());

			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				} else if (str.equals("Call")) {
					call();
				}

				sendAll(name + ">" + str);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			pm.getList().remove(writer);
			sendAll("#" + name + " is out");
			pm.setCount(pm.getCount() - 1);
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

	@Override
	public void call() {
		if(isTurn){
			gc.bet(userNum, gc.getMinimalBet());
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
	}

	@Override
	public void raise(int money) {
		if(isTurn){
			gc.setMinimalBet(gc.getMinimalBet() + money);
			gc.bet(userNum, gc.getMinimalBet());
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
		
	}

	@Override
	public void die() {
		if(isTurn){
			gc.die(userNum);
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
		
	}

	@Override
	public void check() {
		if(isTurn&&check){
			gc.calculateTurn();
			this.isTurn = false;
			this.check = false;
		}
	}

	@Override
	public void exitgame() {
		
	}

	@Override
	public void getTurn() {
		System.out.println("행동을 입력하세요");
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
}