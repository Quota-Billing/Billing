package edu.rosehulman.sharedservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.router.Routes;

public class TierSharedService {

	public TierSharedService(){
		
	}
	
	public Tier UpdateTier(String partnerId, String productId, String quotaId, String tierId) {
		HttpResponse<Tier> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR+"/partner/{partnerId}/product/{productId}/quota/{quotaId}/tier/{tierId}")
					.routeParam("partnerId", partnerId)
					.routeParam("productId", productId)
					.routeParam("quotaId", quotaId)
					.routeParam("tierId", tierId)
					.asObject(Tier.class);
			Tier tier = response.getBody();
			System.out.println(tier==null);
			return tier;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
