package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.datastore.UserDatastore;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierToUserHandler implements Route {

	private TierDatastore tierdatastore;
	private UserDatastore userdatastore;

	public AddTierToUserHandler() {

	}

	public AddTierToUserHandler(UserDatastore d1, TierDatastore d2) {
		this.userdatastore = d1;
		this.tierdatastore = d2;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		String userId = request.params(":userId");
		// System.out.println(userId);
		User user = this.userdatastore.getUser(partnerId, productId, userId);
		Tier tier = this.tierdatastore.getTier(partnerId, productId, userId, quotaId, tierId);

		user.setTier(tier);
		this.userdatastore.updateUser(user);
		return "";
	}

}
