package edu.rosehulman.sharedservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.router.Routes;

public class QuotaSharedService {

	public QuotaSharedService(){
		
	}
	
	public Quota UpdateQuota(String productId, String partnerId, String quotaId) {
		HttpResponse<Quota> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR+"/partner/{partnerId}/product/{productId}/quota/{quotaId}")
					.routeParam("partnerId", partnerId)
					.routeParam("quotaId", quotaId)
					.routeParam("productId", productId)
					.asObject(Quota.class);
			Quota quota = response.getBody();
			return quota;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

}
