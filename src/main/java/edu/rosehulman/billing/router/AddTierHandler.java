package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierHandler implements Route {

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		Partner partner = Database.getInstance().getPartner(partnerId);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
//		Database.getInstance().addTier(partnerId, productId, quotaId, tierId, name, max, price);
		Tier tier = SharedClient.getInstance().UpdateTier(partnerId, productId, quotaId, tierId);
		tier.setPartner(partner);
		tier.setProduct(product);
		tier.setQuota(quota);
		Database.getInstance().addTierDirect(tier);

		return "";
	}

}