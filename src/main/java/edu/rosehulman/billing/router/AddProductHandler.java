package edu.rosehulman.billing.router;

import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddProductHandler implements Route {
	PartnerDatastore Partnerstore;
	ProductDatastore Productstore;

	public AddProductHandler() {

	}

	public AddProductHandler(PartnerDatastore Partnerstore, ProductDatastore Productstore) {
		this.Partnerstore = Partnerstore;
		this.Productstore = Productstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = Partnerstore.getPartner(partnerId);
		Product product = SharedClient.getInstance().UpdateProduct(productId, partnerId);
		Productstore.addProductDirect(product, partner);

		return "";
	}

}
