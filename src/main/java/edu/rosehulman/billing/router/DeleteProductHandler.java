package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteProductHandler implements Route {

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = Database.getInstance().getPartner(partnerId);
		Product product =  SharedClient.getInstance().UpdateProduct(productId, partnerId);
		Database.getInstance().deleteProductDirect(product, partner);

		return "";
	}

}
