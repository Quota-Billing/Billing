package edu.rosehulman.billing.router;
import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;
public class AddUserRouter implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
	    String productId = request.params(":productId");
	    String userId = request.params(":userId");

	    // Add the user to our database
	   // Database.getInstance().addUser(partnerId, productId, userId);
	    Database.getInstance().addUser(partnerId, productId, userId);
	    // Send the user to Billing
	    //BillingClient.getInstance().addUser(partnerId, productId, userId);

	    // Return to Quota a success
	    return "";
	}

}
