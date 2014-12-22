package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

public class ReceiverThread extends Observable implements Runnable{
	Socket socket;
	private String str = null;
	static ClientConditionDisplay clientDisplay;

	ReceiverThread(String args, Socket socket, Thread thread) {
		this.socket = socket;
		clientDisplay = new ClientConditionDisplay(args, this, thread);
	}
		
	public void run() {
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while (true) {
				String str = reader.readLine();
				if (str == null)
					break;
				
				System.out.println(str);
				
				setMeasurements(str);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	

	public void setMeasurements(String str){
    	this.str = str;
        measurementsChanged();
    }

	private void measurementsChanged() {
		// TODO Auto-generated method stub
		setChanged();
        notifyObservers();
	}
	
	public String getstr(){
        return str;
    }

}