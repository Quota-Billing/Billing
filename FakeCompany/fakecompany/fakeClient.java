package fakecompany;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Date;

import javax.swing.JOptionPane;

public class fakeClient {

	private final String USER_AGENT = "Mozilla/5.0";
	static int pathmode = 1;
	static String userName;
	static String password;

	public static void main(String[] args) throws Exception {
		System.out.println("Socket:");
		BufferedReader socketNumberReader = new BufferedReader(new InputStreamReader(System.in));
		String socketNumberString = null;
		try {
			socketNumberString = socketNumberReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int socketNumber = Integer.parseInt(socketNumberString);

		userName = JOptionPane.showInputDialog("Enter your username\n" + "running the date service on port 8080:");
		password = JOptionPane.showInputDialog("Enter your password\n" + "running the date service on port 8080:");
		String serverAddress = JOptionPane
				.showInputDialog("Enter IP Address of a machine that is\n" + "running the date service on port 8080:");
		Socket s = new Socket(serverAddress, socketNumber);

		System.out.println(new Date().toString());
		fakeClient http = new fakeClient();
		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendPost() throws Exception {

		String url = "http://localhost:8080/user/1155/name/" + userName + "/password/" + password
				+ "/token/dsha2jdaskdhakwda";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
}
