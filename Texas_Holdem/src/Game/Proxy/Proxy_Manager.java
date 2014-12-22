package Game.Proxy;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import Game.Game_Data.Table;
import Game.Game_Data.User;

public class Proxy_Manager {
	private List<PrintWriter> list = Collections
			.synchronizedList(new ArrayList<PrintWriter>());

	private static Proxy_Manager instance;
	private List<Thread> threadList;
	private int count;
	private static final int poolsize = 6;
	
	private Proxy_Manager() {
		threadList = new LinkedList();
		count = 0;
	}

	public static Proxy_Manager getInstance() {
		if(instance==null){
			instance=new Proxy_Manager();
		}
		return instance;
	}

	public void addProxy(String message, Socket socket) {
		Proxy proxy;
		if(message.equals("Dealer")){
			proxy=new Dealer_Proxy(socket, 0);
			((Thread)proxy).start();
		}
		else{
			proxy=new User_Proxy(socket, threadList.size());
			((Thread)proxy).start();
		}
		
		threadList.add((Thread) proxy);
		System.out.println(getProxy(threadList.size()-1)+"¿« userNum¿∫"+getProxy(threadList.size()-1).getUserNum());
	}

	public Proxy getProxy(int i) {
		return (Proxy) threadList.get(i);
	}

	public void releaseObject(Object o) {

	}
	public List<PrintWriter> getList() {
		return list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
