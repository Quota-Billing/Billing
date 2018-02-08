package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetBillingHistoryHandler implements Route{
	
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String timestamp = request.params(":timestamp");
		return Database.getInstance().getBillinghistory(timestamp);
	}


}