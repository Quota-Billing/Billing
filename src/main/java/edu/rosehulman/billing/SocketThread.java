package edu.rosehulman.billing;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketThread implements Runnable {

	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(6543);
			while(true) {
				new SocketConnection(server.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
