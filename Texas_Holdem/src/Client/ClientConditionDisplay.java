package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class ClientConditionDisplay implements Observer{

	Observable observable;

	ClientGUI window;
	private static int check = 0;
	String args;

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (o instanceof ReceiverThread) {
			ReceiverThread receiverStr = (ReceiverThread) o;
			String str = receiverStr.getstr();
			System.out.println(str + " " + args);
			
			if(check == 0)	check = 1;
			else {
				String D = str.split("/")[0];
				if (D.equals("userCard"))				window.setframeUserCard(str);
				else if (D.equals("userState"))			window.setframeUserState(str);
				else if (D.equals("userMoney"))			window.setframeUserMoney(str);
				else if (D.equals("tableCard"))			window.setframetableCard(str);
				else if (D.equals("tableMoney"))		window.setframetableMoney(str);
				else if (D.equals("userName")) 			window.setfrmeuserName(str);
			}
		}
		System.out.println("end update");
	}

	public ClientConditionDisplay(String args, Observable observable, Thread thread) {

		this.args = args;
		this.observable = observable;
		observable.addObserver(this);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					window = new ClientGUI();
					window.Myframe(thread);
				//	Client window = new Client();
				//	window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
