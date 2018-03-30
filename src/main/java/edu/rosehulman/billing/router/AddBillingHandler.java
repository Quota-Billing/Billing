package edu.rosehulman.billing.router;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.models.Billing;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddBillingHandler implements Route {
	
	private BillingDatastore billingdatastore;
	private BillingHistoryDatastore historydatastore;
	public AddBillingHandler(){
		
	}
	
	public AddBillingHandler(BillingDatastore store, BillingHistoryDatastore store2){
		this.billingdatastore = store;
		this.historydatastore = store2;
	}

	public Object handle(Request request, Response response) throws Exception {
//		String billingId = request.params(":billingId");
		String userID = request.params(":userID");
		String plan = request.params(":plan");
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		double fee = Double.valueOf(request.params(":fee"));
		// Add the user to our database
		// Database.getInstance().addUser(partnerId, productId, userId);
		//Database.getInstance().addBilling(userID, partnerId, productId, plan, fee);
		this.billingdatastore.addBilling(userID, partnerId, productId, plan, fee);
		Billing bill = this.billingdatastore.getBilling(userID, partnerId, productId);
		this.historydatastore.addBillingHistory(userID, partnerId, productId, bill);
		return "";
	}

}
