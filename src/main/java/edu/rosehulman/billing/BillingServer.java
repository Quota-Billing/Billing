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
import java.util.ArrayList;
import java.util.List;

import edu.rosehulman.billing.router.AddUserRouter;
import edu.rosehulman.billing.router.Routes;

public class BillingServer {
	public static ArrayList dbinfo;
	public static void main(String[] args) {
		port(8084); // Set the port to run on

		Database mydb = new Database();
		dbinfo = mydb.getSharedDatabaseInfo();
		get("/getdb", (req, res) -> "database information get all table name: "+dbinfo);

		post("/addquota", (req, res) -> {
			return mydb.addTobillingdb("2", "quotaName", "2");
		});
		
	}

}
