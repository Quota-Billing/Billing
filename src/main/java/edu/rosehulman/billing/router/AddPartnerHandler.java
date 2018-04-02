package edu.rosehulman.billing.router;

import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Partner;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddPartnerHandler implements Route {
	PartnerDatastore Partnerstore;

	public AddPartnerHandler() {

	}

	public AddPartnerHandler(PartnerDatastore Partnerstore) {
		this.Partnerstore = Partnerstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");

		Partner partner = SharedClient.getInstance().UpdatePartner(partnerId);
		Partnerstore.addPartnerDirect(partner);

		return "";
	}

}
