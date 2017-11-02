package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddBillingRouter implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String billingId = request.params(":billingId");
		String userID = request.params(":userID");
		String plan = request.params(":plan");

		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		Database.getInstance().addBilling(Integer.parseInt(billingId), Integer.parseInt(userID), plan);
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}

}
