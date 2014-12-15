package Game.Proxy;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Proxy_Manager {
	private List<PrintWriter> list = Collections
			.synchronizedList(new ArrayList<PrintWriter>());

	private static Proxy_Manager instance;
	private List<Thread> threadList;
	private int count;
	private static final int poolsize = 10;

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
			proxy=new Dealer_Proxy(socket);
		}
		else{
			proxy=new User_Proxy(socket);
		}
		((Thread)proxy).start();
		threadList.add((Thread) proxy);
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
