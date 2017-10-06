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
		try {
			input = socket.getInputStream();
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String[] elementgener(int num, String startst, String commandReads) {
		String[] element = new String[num];
		int count=0;
		int pre=startst.length();
		for (int i = startst.length(); i < commandReads.length();i++){
			if(commandReads.charAt(i)=='|'){
				element[count]=commandReads.substring(pre, i-1);
				pre=i;
				count++;
			}
		}
		return element;
	}
	@Override
	public void run() {
		try {
			byte array[] = new byte[1024];
			while (true) {
				do {
					int readed = input.read(array);
					String sendString = "";
					System.out.println("readed == " + readed + " " + new String(array).trim());
					String commandReads = new String(array).trim();
					if (commandReads.startsWith("CheckCollections")) {
						System.out.println("read Collections successfully");
					}else {
						System.err.println("Unknown command");
						input.close();
						socket.close();
						continue;
					}
					sendString = new String(sendString.getBytes(), Charset.forName("UTF-8"));
					output.write(sendString);
					output.flush();
				} while (input.available() != 0);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

