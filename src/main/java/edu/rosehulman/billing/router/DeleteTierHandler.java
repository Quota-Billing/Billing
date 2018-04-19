package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.sharedservice.TierSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteTierHandler implements Route {
	private TierDatastore tierdatastore;
	private PartnerDatastore partnerDatastore;
	private TierSharedService service;

	public DeleteTierHandler() {

	}

	public DeleteTierHandler(TierDatastore tierDatastore, PartnerDatastore partnerDatastore,
			TierSharedService service) {
		this.tierdatastore = tierDatastore;
		this.partnerDatastore = partnerDatastore;
		this.service = service;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		Partner partner = this.partnerDatastore.getPartner(partnerId);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);

		Tier tier = service.UpdateTier(partnerId, productId, quotaId, tierId);
		tier.setPartner(partner);
		tier.setProduct(product);
		tier.setQuota(quota);
		this.tierdatastore.deleteTier(tier);

		return "";
	}

}