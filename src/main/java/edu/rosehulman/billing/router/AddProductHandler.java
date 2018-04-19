package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.sharedservice.ProductSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductHandler implements Route {
	PartnerDatastore Partnerstore;
	ProductDatastore Productstore;

	ProductSharedService service;

	public AddProductHandler(PartnerDatastore Partnerstore, ProductDatastore Productstore,
			ProductSharedService productSharedService) {
		this.Partnerstore = Partnerstore;
		this.Productstore = Productstore;
		this.service = productSharedService;
	}

	public AddProductHandler() {
	}

	public Object handle(Request request, Response response) throws Exception {
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = Partnerstore.getPartner(partnerId);
		Product product = this.service.UpdateProduct(productId, partnerId);
		Productstore.addProductDirect(product, partner);

		return "";
	}

}
