package edu.rosehulman.sharedservice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.router.Routes;

public class PartnerSharedService {
	
	public PartnerSharedService(){
		
	}
	
	public Partner UpdatePartner(String partnerId) {
		HttpResponse<Partner> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR+"/partner/{partnerId}")
					.routeParam("partnerId", partnerId)
					.asObject(Partner.class);
			Partner partner = response.getBody();
			return partner;
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
