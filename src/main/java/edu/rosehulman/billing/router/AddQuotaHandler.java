package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.sharedservice.QuotaSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddQuotaHandler implements Route {

	private QuotaDatastore datastore;
	private QuotaSharedService service;
	public AddQuotaHandler(){
		
	}
	
	public AddQuotaHandler(QuotaDatastore datastore, QuotaSharedService service){
		this.datastore = datastore;
		this.service = service;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		Quota quota = this.service.UpdateQuota(productId, partnerId, quotaId);
		Partner partner = Database.getInstance().getPartner(partnerId);
		Product product = partner.getProduct(productId);
		quota.setPartner(partner);
		quota.setProduct(product);
		this.datastore.addQuota(quota);
		return "";
	}

}