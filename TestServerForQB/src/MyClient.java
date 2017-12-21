import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MyClient {

	public static ArrayList user = new ArrayList();

	public static void main(String[] args) throws IOException {

		String userName = JOptionPane
				.showInputDialog("Enter your username\n" + "running the date service on port 9090:");

		if (checkMemeber(userName)) {
			String serverAddress = JOptionPane.showInputDialog(
					"Enter IP Address of a machine that is\n" + "running the date service on port 9090:");
			Socket s = new Socket(serverAddress, 9090);
			BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String answer = input.readLine();
			JOptionPane.showMessageDialog(null, answer);
			System.exit(0);
		} else {
			String newName = JOptionPane.showInputDialog("You haven't registered yet, please input your username:");
			MyServer.adduser(newName);
			System.exit(0);
		}

	}

	private static boolean checkMemeber(String userName) {
		// TODO Auto-generated method stub

		user = MyServer.readfile();
		if (user.contains(userName)) {
			return true;
		} else {
			return false;
		}
		// return false;

	}

}
