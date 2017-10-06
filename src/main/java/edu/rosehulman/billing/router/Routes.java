package edu.rosehulman.billing.router;

public class Routes {
	public static String ADD_USER = "partner/:partnerId/product/:productId/addUser/:userId";
	public static String QUOTA_REACHED = "/partner/:partnerId/product/:productId/user/:userId/quotaReached/:quotaId/";
	
	public static String BILLING_BASE = "/"; // TODO put this in a config file
}
