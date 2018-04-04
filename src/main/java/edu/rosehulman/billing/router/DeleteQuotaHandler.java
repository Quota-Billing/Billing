package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteQuotaHandler implements Route {
	
	private QuotaDatastore store1;
	private PartnerDatastore store2;
	
	public DeleteQuotaHandler(){
		
	}
	
	public DeleteQuotaHandler(QuotaDatastore qstore, PartnerDatastore pstore){
		this.store1 = qstore;
		this.store2 = pstore;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		Quota quota = SharedClient.getInstance().UpdateQuota(productId, partnerId, quotaId);
		Partner partner = this.store2.getPartner(partnerId);
		Product product = partner.getProduct(productId);
		quota.setPartner(partner);
		quota.setProduct(product);
		this.store1.deleteQuota(quota);
		return "";
	}

}