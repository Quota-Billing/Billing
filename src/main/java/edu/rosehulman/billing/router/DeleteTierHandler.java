package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteTierHandler implements Route {
	private TierDatastore tierdatastore;

	public DeleteTierHandler() {

	}

	public DeleteTierHandler(TierDatastore d2) {
		this.tierdatastore = d2;
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
		// Database.getInstance().addTier(partnerId, productId, quotaId, tierId, name,
		// max, price);
		// System.out.println("fasdfgwe");
		Tier tier = SharedClient.getInstance().UpdateTier(partnerId, productId, quotaId, tierId);
		// System.out.println(tier==null);
		tier.setPartner(partner);
		tier.setProduct(product);
		tier.setQuota(quota);
		this.tierdatastore.deleteTier(tier);

		return "";
	}

}