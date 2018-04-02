package edu.rosehulman.billing.router;

import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteProductHandler implements Route {
	PartnerDatastore Partnerstore;
	ProductDatastore Productstore;

	public DeleteProductHandler() {

	}

	public DeleteProductHandler(PartnerDatastore Partnerstore, ProductDatastore Productstore) {
		this.Partnerstore = Partnerstore;
		this.Productstore = Productstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = Partnerstore.getPartner(partnerId);
		Product product = SharedClient.getInstance().UpdateProduct(productId, partnerId);
		// System.out.println(product);
		Productstore.deleteProductDirect(product, partner);

		return "";
	}

}
