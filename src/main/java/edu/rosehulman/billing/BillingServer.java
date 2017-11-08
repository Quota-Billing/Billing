package edu.rosehulman.billing;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;

import edu.rosehulman.billing.router.QuotaReachedRouter;
import edu.rosehulman.billing.router.Routes;

public class BillingServer {
	public static ArrayList<String> dbinfo;

	public static void main(String[] args) {
		port(8085); // Set the port to run on

		dbinfo = Database.getInstance().getSharedDatabaseInfo();
		get("/getdb", (req, res) -> "database information get all table name: " + dbinfo);

		post("/addUser/partner/:partnerId/product/:productId/user/:userId", (req, res) -> {
			//after getting post call, call sharedclient to pull updates
			return SharedClient.getInstance().addUserInfo(req.params(":partnerId"), req.params(":productId"), req.params(":userId"));
			//return Database.getInstance().addTobillingdb(req.params(":partnerId"), req.params(":productId"), req.params(":quotaId"), req.params(":userId"));
		});
		post(Routes.QUOTA_REACHED, new QuotaReachedRouter());

		

		// BrainTree br =new BrainTree();

	}
}
