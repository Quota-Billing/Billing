package edu.rosehulman.billing;

import static spark.Spark.*;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Response;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;
import com.braintreegateway.Request;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

import edu.rosehulman.billing.router.AddUserRouter;
import edu.rosehulman.billing.router.Routes;
import spark.Route;

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
		
//		BrainTree br =new BrainTree();

	}
}
