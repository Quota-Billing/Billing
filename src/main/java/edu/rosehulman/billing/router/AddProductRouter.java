package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductRouter implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String productId = request.params(":productId");
		String productName = request.params(":productName");
		String userId = request.params(":userId");

		Database.getInstance().addProduct(Integer.parseInt(productId), productName, Integer.parseInt(userId));

		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		// Database.getInstance().addProductToPartner(Integer.parseInt(productId));
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}

}
