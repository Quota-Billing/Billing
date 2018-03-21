package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddQuotaHandler implements Route {

	private QuotaDatastore datastore;
	public AddQuotaHandler(){
		
	}
	
	public AddQuotaHandler(QuotaDatastore datastore){
		this.datastore = datastore;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		Quota quota = SharedClient.getInstance().UpdateQuota(productId, partnerId, quotaId);
		Partner partner = Database.getInstance().getPartner(partnerId);
		Product product = partner.getProduct(productId);
		quota.setPartner(partner);
		quota.setProduct(product);
		this.datastore.addQuota(quota);
		return "";
	}

}