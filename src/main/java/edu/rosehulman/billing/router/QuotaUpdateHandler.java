package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.QuotaClient;
import edu.rosehulman.billing.models.Quota;
import spark.Request;
import spark.Response;
import spark.Route;

public class QuotaUpdateHandler implements Route{
	Quota quota;
	
	public QuotaUpdateHandler(Quota quota){
		this.quota = quota;
	}

	public Object handle(Request request, Response response) throws Exception {
		String partnerId = request.params(":partnerId");
	    String productId = request.params(":productId");
	    String userId = request.params(":userId");
	    String tierId = request.params(":tierId");
	    QuotaClient.getInstance().notifyQuota(partnerId, productId, userId);

	   
	    return "";
	}

}
