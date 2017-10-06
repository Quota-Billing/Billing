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
		while (true) {
			System.out.println("Socket:");
			int socketNumber = 5697;

			Socket socket = null;
			String host = "35.164.184.155";
			try {
				socket = new Socket(host, socketNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// DO STUFF HERE.
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// }
}
