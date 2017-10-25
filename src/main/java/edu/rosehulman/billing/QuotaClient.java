package edu.rosehulman.billing;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.router.Routes;

public class QuotaClient {
	private static QuotaClient instance;
	
	private QuotaClient(){
	}
	
	public static QuotaClient getInstance(){
		if (instance == null) {
		      instance = new QuotaClient();
		    }
		    return instance;
	}
	
	public String notifyQuota(String partnerId, String productId, String userId) throws Exception{
		 HttpResponse<String> response = Unirest.post(Routes.QUOTA_ADDR + "partner/{partnerId}/product/{productId}/user/{userId}")
				.routeParam("partnerId", partnerId).routeParam("productId", productId).routeParam("userId", userId)
				.routeParam("userId", userId).asString();

		if (response.getStatus() != 200) {
			System.out.println(response.getStatus());
			throw new Exception();
		} else{
			return "ok";
		}
	}
}
