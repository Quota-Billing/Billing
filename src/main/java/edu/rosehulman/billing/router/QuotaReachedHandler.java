package edu.rosehulman.billing.router;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class QuotaReachedHandler implements Route {
	//path /partner/:partnerId/product/:productId/user/:userId/quotaReached/:quotaId/
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String userId = request.params(":userId");
		String quotaId = request.params(":quotaId");
		
		JSONObject tierObject = new JSONObject(request.body());
		String billingInfo = Database.getInstance().getPartnerBillingInfo(partnerId, productId, userId);
		//Quota quota = Database.getInstance().getQuotaInfo(partnerId, productId, userId, quotaId);
		double totalPrice = 0.0;
		StringBuilder builder = new StringBuilder();
		builder.append(billingInfo);
		builder.append("-------------Current Usage-------------\n");
		
		System.out.println("lol==============");
		System.out.println(tierObject.toString());
		Integer max = tierObject.getInt("max");
		//Integer current = tierObject.getInt("current");
		String name = tierObject.getString("name");
		Integer price = tierObject.getInt("price");
		
		builder.append(name + ": Limit: " + max + " Price: " + price +"\n");
//		if(tierObject != null) {
//		    int max = 0;
//		    int current = 0;
//		    String name = null;
//		    double price = 0;
//		    
//		    
//		    
//		    builder.append(name + ": Limit: " + max + " Current: " + current + " Price: " + price +"\n");
//		}
	
		
		
//		for (Tier t: quota.getTiers()) {
//			int max = t.getMax();
//			int current = t.getValue();
//			String name = t.getName();
//			double price = t.getPrice();
//			
//			if (current >=max) {				
//				totalPrice += price;
//			}
//			
//		}
		
		builder.append("Total Price: " + totalPrice);
		
		// Should bill user in the later stage
		// but for now it just print the bill
		return builder.toString();
	}

}
