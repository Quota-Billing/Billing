package fakecompany;

import spark.Request;
import spark.Response;
import spark.Route;

public class addUserController implements Route{

	 public Object handle(Request request, Response response) throws Exception {
		    String userId = request.params(":userId");
		    String password = request.params(":password");
		    String name = request.params(":name");
		    String token =request.params(":token");
		    // Add the user to our database
		    Database db = Database.getInstance();
		    db.addUser(Integer.parseInt(userId), password, name, token);
		    // Send the user to Billing
		    //BillingClient.getInstance().addUser(partnerId, productId, userId);

		    // Return to Quota a success
		    return "";
		  }
}
