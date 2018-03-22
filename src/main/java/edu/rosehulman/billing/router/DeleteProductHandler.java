package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.SharedClient;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.datastore.PartnerDatastore;
import edu.rosehulman.datastore.ProductDatastore;
import spark.Request;
import spark.Response;
import spark.Route;

public class DeleteProductHandler implements Route {
	PartnerDatastore Partnerstore;
	ProductDatastore Productstore;
	
	public DeleteProductHandler(){
		
	}

	public DeleteProductHandler(PartnerDatastore Partnerstore, ProductDatastore Productstore) {
		this.Partnerstore = Partnerstore;
		this.Productstore = Productstore;
	}

	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String productId = request.params(":productId");
		String partnerId = request.params(":partnerId");
		Partner partner = Partnerstore.getPartner(partnerId);
		Product product =  SharedClient.getInstance().UpdateProduct(productId, partnerId);
		System.out.println(product);
		Productstore.deleteProductDirect(product, partner);

		return "";
	}

}
