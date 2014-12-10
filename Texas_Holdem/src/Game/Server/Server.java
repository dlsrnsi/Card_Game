package Game.Server;

import Game.Proxy.Dealer_Proxy;
import Game.Proxy.Proxy_Manager;
import Game.Proxy.User_Proxy;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int D = 0;
		Proxy_Manager pm=Proxy_Manager.getInstance();

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8989);
			while (true) {
				getTime();
				Socket socket = serverSocket.accept();
				if (D == 0) {
					pm.addProxy("Dealer",socket);
					D++;
				} else {
					pm.addProxy("User",socket);
				}
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
