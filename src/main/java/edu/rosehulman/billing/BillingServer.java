package edu.rosehulman.billing;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import edu.rosehulman.billing.router.AddUserRouter;
import edu.rosehulman.billing.router.Routes;

public class BillingServer {
	public static void main(String[] args) {
		//port(8084); // Set the port to run on

		staticFiles.location("/public");

		post(Routes.ADD_USER, new AddUserRouter());

		try {
			ServerSocket serverSocket = new ServerSocket(8084);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
