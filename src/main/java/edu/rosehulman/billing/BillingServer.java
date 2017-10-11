package edu.rosehulman.billing;

import static spark.Spark.*;
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
		port(8084); // Set the port to run on

		get("/getdb", (req, res) -> "should get something from db");

		
		
	}

}
