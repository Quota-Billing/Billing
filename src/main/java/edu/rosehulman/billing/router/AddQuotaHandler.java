package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddQuotaHandler implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
//		String partnerId = request.params(":partnerId");
//		String name = request.params(":name");
//		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
//		String type = request.params(":type");
		
//		Database.getInstance().addQuota(partnerId, productId, quotaId, name, type);
		SharedClient.getInstance().UpdateQuota(quotaId);
		return "";
	}

}