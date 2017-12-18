package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteBillingHandler implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String billingId = request.params(":billingId");
		String userID = request.params(":userID");
		String plan = request.params(":plan");
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		double fee = Double.valueOf(request.params(":fee"));
		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		Database.getInstance().deleteBilling(userID, partnerId, productId, plan, fee);
		return "";
	}

}
