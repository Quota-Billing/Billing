package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
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
	    
	    // if (this.quota.type == TIME_RECURRING){
	    //	
	    //	if(quota.timeReached()){
	    //    QuotaService.send()
	    //  }
	    //}

	   
	    return "";
	}

}
