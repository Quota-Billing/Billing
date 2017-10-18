package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class QuotaReachedHandler implements Route {
	//path /partner/:partnerId/produc/:productId/user/:userId/quotaReached/:quotaId/
	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String userId = request.params(":userId");
		String quotaId = request.params(":quotaId");

		String billingInfo = Database.getInstance().getPartnerBillingInfo(partnerId, productId, userId);
		Quota quota = Database.getInstance().getQuotaInfo(partnerId, productId, userId, quotaId);
		double totalPrice = 0.0;
		StringBuilder builder = new StringBuilder();

		builder.append(billingInfo);
		builder.append("-------------Current Usage-------------\n");
		for (Tier t: quota.getTiers()) {
			int max = t.getMax();
			int current = t.getValue();
			String name = t.getName();
			double price = t.getPrice();
			
			if (current >=max) {				
				totalPrice += price;
			}
			builder.append(name + ": Limit: " + max + " Current: " + current + " Price: " + price +"\n");
		}
		
		builder.append("Total Price: " + totalPrice);
		
		// Should bill user in the later stage
		// but for now it just print the bill
		return builder.toString();
	}

}
