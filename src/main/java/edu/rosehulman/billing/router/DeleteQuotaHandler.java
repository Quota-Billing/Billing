package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.sharedservice.QuotaSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteQuotaHandler implements Route {

	private QuotaDatastore quotaDatastore;
	private PartnerDatastore partnerDatastore;
	private QuotaSharedService service;

	public DeleteQuotaHandler() {

	}

	public DeleteQuotaHandler(QuotaDatastore qstore, PartnerDatastore pstore, QuotaSharedService service) {
		this.quotaDatastore = qstore;
		this.partnerDatastore = pstore;
		this.service = service;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String quotaId = request.params(":quotaId");
		Quota quota = this.service.UpdateQuota(productId, partnerId, quotaId);
		Partner partner = this.partnerDatastore.getPartner(partnerId);
		Product product = partner.getProduct(productId);
		quota.setPartner(partner);
		quota.setProduct(product);
		this.quotaDatastore.deleteQuota(quota);
		return "";
	}

}