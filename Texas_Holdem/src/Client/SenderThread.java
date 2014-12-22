package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends Thread {
	Socket socket;
	String userName;
	String str;

	SenderThread(Socket socket, String userName) {
		this.socket = socket;
		this.userName = userName;
	}

	public void run() {
		try {
			sendMsg();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (Exception ignored) {
			}
		}
	}
	
	public void sendMsg() throws IOException{

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.println(userName);
		writer.flush();
		
		while (true) {
			str = reader.readLine();
			if (str.equals("Exit"))
				break;
			writer.println(str);
			writer.flush();
		}
	}
	
	public void setstr(String str) throws IOException{
		this.str = str;

		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		writer.println(str);
		writer.flush();
	}
}
