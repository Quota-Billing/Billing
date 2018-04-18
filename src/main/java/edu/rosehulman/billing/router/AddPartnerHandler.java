package edu.rosehulman.billing.router;

import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.sharedservice.PartnerSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddPartnerHandler implements Route {
	PartnerDatastore Partnerstore;
	PartnerSharedService service;
	
	public AddPartnerHandler(){
		
	}
	
	public AddPartnerHandler(PartnerDatastore Partnerstore, PartnerSharedService service){
		this.Partnerstore = Partnerstore;
		this.service = service;
	}

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");

		Partner partner = service.UpdatePartner(partnerId);
		Partnerstore.addPartnerDirect(partner);

		return "";
	}

}
