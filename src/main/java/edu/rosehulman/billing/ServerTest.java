package edu.rosehulman.billing;

public class ServerTest {
	
	public static void main(String[] args) {
		Thread thr = new Thread(new SocketThread());
		thr.start();
	}

}
