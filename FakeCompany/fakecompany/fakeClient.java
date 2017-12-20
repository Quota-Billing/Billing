package fakecompany;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class fakeClient {
	
	static int pathmode = 1;

	public static void main(String[] args)throws IOException {
		System.out.println("Socket:");
		BufferedReader socketNumberReader = new BufferedReader(new InputStreamReader(System.in));
		String socketNumberString = null;
		try {
			socketNumberString = socketNumberReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int socketNumber = Integer.parseInt(socketNumberString);

		String userName = JOptionPane
				.showInputDialog("Enter your username\n" + "running the date service on port 9090:");

			String serverAddress = JOptionPane.showInputDialog(
					"Enter IP Address of a machine that is\n" + "running the date service on port 9090:");
			Socket s = new Socket(serverAddress, socketNumber);
			System.out.println(new Date().toString());
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
