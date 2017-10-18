package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddPartnerRouter implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String partnerName = request.params(":partnerName");
		String productId = request.params(":productId");
		String password = request.params(":password");

		Database.getInstance().addPartner(Integer.parseInt(partnerId), partnerName, Integer.parseInt(productId));

		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
//		Database.getInstance().addProductToPartner(Integer.parseInt(partnerId), Integer.parseInt(productId));
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}

}
