package edu.rosehulman.billing;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

// this is a client connecting to sharedservice so we can pull updates
public class SharedClient {
	private static SharedClient instance;

	  private SharedClient() {
	  }

	  public static synchronized SharedClient getInstance() {
	    if (instance == null) {
	      instance = new SharedClient();
	    }
	    return instance;
	  }

//	  public void addUser(String partnerId, String productId, String quotaId, String userId) throws Exception {
//	    // Send Billing the user
//
//	    HttpResponse<String> response = Unirest.post("http://localhost:8081/" + "partner/{partnerId}/product/{productId}quota/{quotaId}/user/{userId}")
//	        .routeParam("partnerId", partnerId)
//	        .routeParam("productId", quotaId)
//	        .routeParam("quotaId", quotaId)
//	        .routeParam("userId", userId)
//	        .asString();
//
//	    if (response.getStatus() != 200) {
//	    	System.out.println(response.getStatus());
//	    	throw new Exception();
//	    }
//	  }
	  
	  public void addPartner(String partnerId){
		  
	  }

	
	public String addUserInfo(String partnerId, String productId, String userId) throws Exception {
//		HttpResponse<JsonNode> response = Unirest.get("http://localhost:8085/getUser" + "partner/{partnerId}/product/{productId}quota/{quotaId}/user/{userId}")
//				.routeParam("partnerId", partnerId)
//		        .routeParam("productId", productId)
//		        .routeParam("userId", userId)
//		        .asJson();
//		 if (response.getStatus() != 200) {
//		    	System.out.println(response.getStatus());
//		    	throw new Exception();
//		 }
//		 JsonNode result = response.getBody();
//		 System.out.println(result.toString());
//		 JSONObject ids = result.getObject();
		 // will use for adding other models.
		 //Database.getInstance().addUser(ids.getString("partnerId"), ids.getString("productId"), ids.getString("userId"));
		System.out.println(productId);
		Database.getInstance().addUser(partnerId, productId, userId);
		 return "ok";
	}
}
