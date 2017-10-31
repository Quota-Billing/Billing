package edu.rosehulman.billing.router;

public class Routes {
	public static String ADD_USER = "partner/:partnerId/product/:productId/addUser/:userId";
	public static String QUOTA_REACHED = "/partner/:partnerId/produc/:productId/user/:userId/quotaReached/:quotaId/";
	public static String QUOTA_ADDR = "http://localhost:8082/";
	//public static String QUOTA_ADDR_notify = "http://localhost:8082/";
	public static String BILLING_BASE = "/"; // TODO put this in a config file
}
