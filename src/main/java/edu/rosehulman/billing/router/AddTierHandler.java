package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.sharedservice.TierSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierHandler implements Route {
	
	private TierDatastore tierdatastore;
	private TierSharedService tierSharedService;
	public AddTierHandler(){
		
	}
	
	public AddTierHandler( TierDatastore d2, TierSharedService service){
		this.tierdatastore = d2;
		this.tierSharedService = service;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		Partner partner = Database.getInstance().getPartner(partnerId);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		System.out.println(quota.getId());

//		Database.getInstance().addTier(partnerId, productId, quotaId, tierId, name, max, price);
		Tier tier = tierSharedService.UpdateTier(partnerId, productId, quotaId, tierId);
		tier.setPartner(partner);
		tier.setProduct(product);
		tier.setQuota(quota);
		this.tierdatastore.addTier(tier);

		return "";
	}

}