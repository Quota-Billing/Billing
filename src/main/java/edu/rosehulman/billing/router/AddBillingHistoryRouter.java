package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddBillingHistoryRouter implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String billinghistoryId = request.params(":billinghistoryId");
		String time_stamp = request.params(":time_stamp");
		String fee = request.params(":fee");

		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		Database.getInstance().addBillingHistory(Integer.parseInt(billinghistoryId), time_stamp, Double.parseDouble(fee));
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}

}
