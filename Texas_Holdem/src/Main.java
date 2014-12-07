import Game.Game_Data.Game_Controller;
import ServerClient.PerClientThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
	static Game_Controller gc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gc = new Game_Controller();
		gc.startGame(3, 0, 0);
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8989);
			while (true) {
				getTime();
				Socket socket = serverSocket.accept();
				Thread thread = new PerClientThread(socket);
				thread.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	static String getTime() {
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	} // getTime

}
