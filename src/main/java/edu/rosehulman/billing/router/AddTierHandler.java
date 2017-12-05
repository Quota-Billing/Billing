package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierHandler implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
//		String partnerId = request.params(":partnerId");
//		String name = request.params(":name");
//		String productId = request.params(":productId");
//		String quotaId = request.params(":quotaId");
		String tierId = request.params(":tierId");
//		String price = request.params(":price");
//		String max = request.params("max");
		
//		Database.getInstance().addTier(partnerId, productId, quotaId, tierId, name, max, price);
		SharedClient.getInstance().UpdateTier(tierId);
		
		return "";
	}

}