package edu.rosehulman.billing.router;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class UpdateUserTierHandler implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String userId = request.params(":userId");
		User user = Database.getInstance().getUser(partnerId, productId, userId);
		String body = request.body();
		Tier tier = new ObjectMapper().readValue(body, Tier.class);
		user.setTier(tier);
		Database.getInstance().updateUser(user);
		return "";
	}

}
