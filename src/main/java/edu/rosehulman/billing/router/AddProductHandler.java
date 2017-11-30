package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductHandler implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String productId = request.params(":productId");
//		String productName = request.params(":productName");
//		String partnerId = request.params(":partnerId");

//		Database.getInstance().addProductToPartner(partnerId, productName, productId);
		SharedClient.getInstance().UpdateProduct(productId);
		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		// Database.getInstance().addProductToPartner(Integer.parseInt(productId));
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}

}
