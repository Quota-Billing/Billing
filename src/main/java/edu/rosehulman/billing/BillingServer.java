package edu.rosehulman.billing;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;

public class BillingServer {
	public static ArrayList<String> dbinfo;
	public static void main(String[] args) {
		port(8085); // Set the port to run on

		
		dbinfo = Database.getInstance().getSharedDatabaseInfo();
		get("/getdb", (req, res) -> "database information get all table name: "+dbinfo);

		post("/addUser/partner/:partnerId/quota/:quotaId/user/:userId", (req, res) -> {
			return Database.getInstance().addTobillingdb(req.params(":partnerId"), req.params(":quotaId"), req.params(":userId"));
		});
		
//		BrainTree br =new BrainTree();

	}
}
