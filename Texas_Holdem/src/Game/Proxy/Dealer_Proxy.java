package Game.Proxy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

import Game.Server.Game_Controller;

public class Dealer_Proxy extends Thread implements Proxy {
	Game_Controller gc;
	Proxy_Manager pm;
	Socket socket;
	ReentrantLock lock;

	PrintWriter writer;
	int userNum;
	int numOfJoker = 0;
	int minimalBet = 1;
	boolean check;

	public Dealer_Proxy(Socket socket, int userNum) {
		gc = Game_Controller.getInstance();
		pm = Proxy_Manager.getInstance();
		lock = new ReentrantLock();
		this.socket = socket;
		this.userNum = userNum;
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
			name += "(Dealer)";
			pm.setCount(pm.getCount() + 1);
			sendAll("#" + name + " is Joined" + pm.getCount());

			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				} else if (str.equals("StartGame") == true)
					startGame(name);

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

	public void startGame(String name) {
		lock.lock();
		sendAll(name + " start game");
		gc.startGame(pm.getCount(), 0, 0);
		lock.unlock();
	}

	public void changeOption(int numOfJoker, int minimalBet) {
		lock.lock();
		this.numOfJoker = numOfJoker;
		this.minimalBet = minimalBet;
		lock.unlock();
	}

	@Override
	public void call() {
		gc.bet(userNum, gc.getMinimalBet());
		gc.calculateTurn();
		lock.unlock();
	}

	@Override
	public void raise(int money) {
		gc.setMinimalBet(gc.getMinimalBet()+money);
		gc.bet(userNum,gc.getMinimalBet());
		gc.calculateTurn();
		lock.unlock();
	}

	@Override
	public void die() {
		gc.die(userNum);
		gc.calculateTurn();
		lock.unlock();
	}

	@Override
	public void check() {
		gc.calculateTurn();
		lock.unlock();
	}


	@Override
	public void exitgame() {
		// TODO Auto-generated method stub
	}

	@Override
	public void getTurn() {
		// TODO Auto-generated method stub
		lock.lock();
		System.out.println("행동을 입력하세요");
	}

	@Override
	public int getUserNum() {
		// TODO Auto-generated method stub
		return userNum;
	}

}