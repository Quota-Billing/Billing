package edu.rosehulman.billing;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;

class SocketConnection extends Thread {
	InputStream input;
	PrintWriter output;
	Socket socket;

	public SocketConnection(Socket socket) {
		super("Thread 1");
		this.socket = socket;
	}

}

