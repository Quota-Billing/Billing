package edu.rosehulman.billing.router;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;
import spark.Route;

public class QuotaReachedHandler implements Route {
	// path
	// /partner/:partnerId/product/:productId/user/:userId/quotaReached/:quotaId/

	private BillingDatastore billingdatastore;
	private BillingHistoryDatastore historydatastore;
	private TierDatastore tierdatastore;

	public QuotaReachedHandler() {

	}

	public QuotaReachedHandler(BillingDatastore store, BillingHistoryDatastore store2, TierDatastore store3) {
		this.billingdatastore = store;
		this.historydatastore = store2;
		this.tierdatastore = store3;
	}

	public Object handle(Request request, Response response) throws Exception {
		// System.out.println(request.body());
		// System.out.println(request.toString());
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String userId = request.params(":userId");
		String quotaId = request.params(":quotaId");
		String tierId = request.params(":tierId"); // suppose to be included

		Tier tierObject = tierdatastore.getTier(partnerId, productId, userId, quotaId, tierId);

		// Tier tierObject = Database.getInstance().getTier(tierId);

		// need to find the right tier object
		// String billingInfo = Database.getInstance().getPartnerBillingInfo(partnerId,
		// productId, userId);
		// //Quota quota = Database.getInstance().getQuotaInfo(partnerId, productId,
		// userId, quotaId);
		double totalPrice = 0.0;
		// StringBuilder builder = new StringBuilder();
		// builder.append(billingInfo);
		// builder.append("-------------Current Usage-------------\n");
		//
		// System.out.println(tierObject.toString());
		Integer max = tierObject.getMax();
		// Integer current = tierObject.getInt("current");
		String name = tierObject.getName();
		double price = tierObject.getPrice();
		totalPrice += price;
		// builder.append(name + ": Limit: " + max + " Price: " + price +"\n");
		// if(tierObject != null) {
		// int max = 0;
		// int current = 0;
		// String name = null;
		// double price = 0;
		//
		//
		//
		// builder.append(name + ": Limit: " + max + " Current: " + current + " Price: "
		// + price +"\n");
		// }

		// for (Tier t: quota.getTiers()) {
		// int max = t.getMax();
		// int current = t.getValue();
		// String name = t.getName();
		// double price = t.getPrice();
		//
		// if (current >=max) {
		// totalPrice += price;
		// }
		//
		// }

		// builder.append("Total Price: " + totalPrice);

		// Should bill user in the later stage
		// but for now it just print the bill

		this.billingdatastore.addBilling(userId, partnerId, productId, "credit card", totalPrice);
		Billing bill = this.billingdatastore.getBilling(userId, partnerId, productId);
		this.historydatastore.addBillingHistory(userId, partnerId, productId, bill);
		// Fakecompany
		// QuotaClient.getInstance().notifyQuota(partnerId, productId, userId);
		Partner partner = Database.getInstance().getPartner(partnerId);
		String webhook = partner.getWebhook();
		HttpResponse<String> partnerResponse = Unirest.post(webhook + "user/{userId}").routeParam("userId", userId)
				.asString();

		if (partnerResponse.getStatus() != 200) {
			System.out.println(partnerResponse.getStatus());
			throw new Exception();
		}
		// master
		// Partner partner = Database.getInstance().getPartner(partnerId);
		// String url = partner.getApiKey();
		// HttpResponse<String> billingresponse;
		// try {
		// billingresponse = Unirest.get(url+"user/{userId}")
		// .routeParam("userId", userId)
		// .asString();
		// System.out.println(billingresponse);
		// } catch (UnirestException e) {
		// e.printStackTrace();
		// }
		return "";
	}

}
