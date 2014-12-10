package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiverThread extends Thread {
	Socket socket;

	ReceiverThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			while (true) {
				String str = reader.readLine();
				if (str == null) {
					break;
				}
				System.out.println(str);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}