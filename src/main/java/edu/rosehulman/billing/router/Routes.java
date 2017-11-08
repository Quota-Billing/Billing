package edu.rosehulman.billing.router;

public class Routes {
	public static final String ADD_BILLING = "userId/:userID/plan/:plan/partnerId/:partnerId/productId/:productId/fee/:fee";
	
	public static final String ADD_BILLING_HISTORY = "billingId/:billingId/fee/:fee";
	
	public static final String ADD_USER = "partner/:partnerId/product/:productId/addUser/:userId";
	
	public static final String QUOTA_REACHED = "/partner/:partnerId/product/:productId/user/:userId/quotaReached/:quotaId/";
	
	public static final String QUOTA_ADDR = "http://localhost:8082/";
	
	public static final String ADD_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/type/:type";
		
	public static final String ADD_PRODUCT_TO_PARTNER = "partner/:partnerId/name/:name/product/:productId";
	
	public static final String ADD_PARTNER = "partner/:partnerId/name/:name/key/:api_key/password/:password";

	public static final String ADD_Tier = "partner/:partnerId/product/:productId/quota/:quotaId/name/:name/tier/:tierId/price/:price/max/:max";


}
