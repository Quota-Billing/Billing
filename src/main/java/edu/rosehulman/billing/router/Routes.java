package edu.rosehulman.billing.router;

public class Routes {
	public static final String ADD_BILLING = "userId/:userID/plan/:plan/partnerId/:partnerId/productId/:productId/fee/:fee";

	public static final String ADD_BILLING_HISTORY = "billingId/:billingId/fee/:fee";

	public static final String ADD_USER = "partner/:partnerId/product/:productId/addUser/:userId";

	public static final String ADD_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId";

	public static final String ADD_PRODUCT_TO_PARTNER = "partner/:partnerId/product/:productId";

	public static final String ADD_PARTNER = "partner/:partnerId";

	public static final String ADD_Tier = "partner/:partnerId/product/:productId/quota/:quotaId/tier/:tierId";

	public static final String DELETE_USER = "partner/:partnerId/product/:productId/deleteUser/:userId";
	
	public static final String DELETE_BILLING = "userId/:userID/plan/:plan/partnerId/:partnerId/productId/:productId/fee/:fee";

	public static final String DELETE_BILLING_HISTORY = "billingId/:billingId/fee/:fee";

	public static final String DELETE_QUOTA = "partner/:partnerId/product/:productId/quota/:quotaId";

	public static final String DELETE_PRODUCT_TO_PARTNER = "partner/:partnerId/product/:productId";

	public static final String DELETE_PARTNER = "partner/:partnerId";

	public static final String DELETE_Tier = "partner/:partnerId/product/:productId/quota/:quotaId/tier/:tierId";
	
	public static final String QUOTA_REACHED = "/partner/:partnerId/product/:productId/user/:userId/quotaReached/:quotaId";
	
	public static final String UPDATE_USER_TIER = "/partner/:partnerId/product/:productId/user/:userId";
	
	public static final String QUOTA_ADDR = "http://quota.csse.rose-hulman.edu:8084/";
	// public static String QUOTA_ADDR_notify = "http://localhost:8082/";
	public static String BILLING_BASE = "/"; // TODO put this in a config file
}
