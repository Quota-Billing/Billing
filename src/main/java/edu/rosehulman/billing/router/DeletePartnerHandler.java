package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.datastore.PartnerDatastore;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeletePartnerHandler implements Route {
	PartnerDatastore Partnerstore;
	
	public DeletePartnerHandler(){

	}
	
	public DeletePartnerHandler(PartnerDatastore Partnerstore){
		this.Partnerstore = Partnerstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");

		Partner partner = SharedClient.getInstance().UpdatePartner(partnerId);
		Partnerstore.deletePartnerDirect(partner);

		return "";
	}

}
