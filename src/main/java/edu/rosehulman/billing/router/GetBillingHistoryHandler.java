package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetBillingHistoryHandler implements Route{
	
	private BillingHistoryDatastore historystore;
	
	public GetBillingHistoryHandler(){
		
	}
	
	public GetBillingHistoryHandler(BillingHistoryDatastore store){
		this.historystore = store;
	}
	
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String id = request.params(":id");
		return historystore.getBillingHistory(id);
	}


}