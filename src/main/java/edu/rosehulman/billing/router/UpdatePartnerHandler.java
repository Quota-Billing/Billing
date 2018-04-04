package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

public class UpdatePartnerHandler implements Route {

	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		Partner partner = SharedClient.getInstance().UpdatePartner(partnerId);
		Database.getInstance().updatePartner(partner);
		return "";
	}

}
