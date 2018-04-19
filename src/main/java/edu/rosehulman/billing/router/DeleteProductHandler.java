package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.sharedservice.ProductSharedService;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteProductHandler implements Route {
	PartnerDatastore partnerstore;
	ProductDatastore productstore;
	ProductSharedService service;

	public DeleteProductHandler() {

	}

	public DeleteProductHandler(PartnerDatastore partnerstore, ProductDatastore productstore,
			ProductSharedService service) {
		this.partnerstore = partnerstore;
		this.productstore = productstore;
		this.service = service;
	}

	public Object handle(Request request, Response response) throws Exception {
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = partnerstore.getPartner(partnerId);
		Product product = service.UpdateProduct(productId, partnerId);
		// System.out.println(product);
		productstore.deleteProductDirect(product, partner);

		return "";
	}

}
