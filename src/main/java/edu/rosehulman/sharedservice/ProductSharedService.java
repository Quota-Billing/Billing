package edu.rosehulman.sharedservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.router.Routes;

public class ProductSharedService {
	public ProductSharedService() {

	}

	public Product UpdateProduct(String productId, String partnerId) {

		HttpResponse<Product> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR + "/partner/{partnerId}/product/{productId}")
					.routeParam("partnerId", partnerId).routeParam("productId", productId).asObject(Product.class);
			Product product = response.getBody();
			return product;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
