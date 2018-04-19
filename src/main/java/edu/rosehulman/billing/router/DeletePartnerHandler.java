package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.sharedservice.PartnerSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeletePartnerHandler implements Route {
	private PartnerDatastore Partnerstore;
	private PartnerSharedService service;

	public DeletePartnerHandler() {

	}

	public DeletePartnerHandler(PartnerDatastore Partnerstore, PartnerSharedService service) {
		this.Partnerstore = Partnerstore;
		this.service = service;
	}

	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");

		Partner partner = this.service.UpdatePartner(partnerId);
		Partnerstore.deletePartnerDirect(partner);

		return "";
	}

}
