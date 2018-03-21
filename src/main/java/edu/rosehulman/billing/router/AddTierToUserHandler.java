package edu.rosehulman.billing.router;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class AddTierToUserHandler implements Route{
	
	private TierDatastore tierdatastore;
	
	public AddTierToUserHandler(){
		
	}
	
	public AddTierToUserHandler( TierDatastore d2){
		this.tierdatastore = d2;
	}


	@Override
	public Object handle(Request request, Response response) throws Exception {
		// TODO Auto-generated method stub
		String partnerId = request.params(":partnerId");
		String productId = request.params(":productId");
		String tierId = request.params(":tierId");
		String quotaId = request.params(":quotaId");
		String userId = request.params(":userId");

		User user = Database.getInstance().getUser(partnerId, productId, userId);
		Tier tier = this.tierdatastore.getTier(partnerId, productId, userId, quotaId, tierId);

		user.setTier(tier);
		Database.getInstance().updateUser(user);
		return "";
	}

}
