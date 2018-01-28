package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierToUserHandler implements Route{

	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		String userId = request.params(":userId");
		User user = Database.getInstance().getUser(partnerId, productId, userId);
		Tier tier = Database.getInstance().getTier(partnerId, productId, quotaId);
		user.setTier(tier);
		
		return "";
	}

}
