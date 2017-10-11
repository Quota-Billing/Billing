package edu.rosehulman.billing;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class BillingClient {
	static int pathmode = 1;

	public static void main(String[] args) {
		System.out.println("Socket:");
		BufferedReader socketNumberReader = new BufferedReader(new InputStreamReader(System.in));
		String socketNumberString = null;
		try {
			socketNumberString = socketNumberReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int socketNumber = Integer.parseInt(socketNumberString);

		Socket socket = null;
		String host = "csse@srproj-18.csse.rose-hulman.edu";
		try {
			socket = new Socket(host, socketNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// DO STUFF HERE.
		System.out.println("!!!");
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// }
}
