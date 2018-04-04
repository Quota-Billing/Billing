package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.BillingDatastore;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteBillingHandler implements Route {

	private BillingDatastore billingdatastore;

	public DeleteBillingHandler() {

	}

	public DeleteBillingHandler(BillingDatastore store) {
		this.billingdatastore = store;
	}

	public Object handle(Request request, Response response) throws Exception {
		String billingId = request.params(":billingId");
		String userID = request.params(":userID");
		String plan = request.params(":plan");
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		double fee = Double.valueOf(request.params(":fee"));
		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		this.billingdatastore.deleteBilling(userID, partnerId, productId);
		return "";
	}

}
