package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.UserDatastore;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddUserHandler implements Route {
	UserDatastore userstore;

	public AddUserHandler() {

	}

	public AddUserHandler(UserDatastore userstore) {
		this.userstore = userstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String userId = request.params(":userId");

		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		userstore.addUser(userId, productId, partnerId);
		return "";
	}

}
