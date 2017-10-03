package edu.rosehulman.billing;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import edu.rosehulman.billing.router.AddUserRouter;
import edu.rosehulman.billing.router.Routes;

public class BillingServer {
	public static void main(){
		port(8082); // Set the port to run on

		staticFiles.location("/public");

		post(Routes.ADD_USER, new AddUserRouter());
	}
	
}
