package fakecompany;

import spark.Request;
import spark.Response;
import spark.Route;

public class addTierController implements Route {

	public Object handle(Request request, Response response) throws Exception {
		String tierId = request.params(":tierId");
		String name = request.params(":name");
		String max = request.params(":max");
		String price = request.params(":price");
		String graceExtra = request.params(":graceExtra");
		// Add the user to our database
		Database db = Database.getInstance();
		db.addTier(tierId, name, Integer.parseInt(max), Double.parseDouble(price), Integer.parseInt(graceExtra));
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// db.addUser(userId, password, token);
		// Send the user to Billing
		// BillingClient.getInstance().addUser(partnerId, productId, userId);

		// Return to Quota a success
		return "";
	}
}
