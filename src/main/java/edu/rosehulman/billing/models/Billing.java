package edu.rosehulman.billing.models;

public class Billing {
	
	private int billingID;
	private int userID;
	private String plan;
	
	public Billing(int bid, int uid, String plan){
		this.billingID = bid;
		this.userID = uid;
		this.plan = plan;
	}
	
	public int getUserId(){
		return this.userID;
	}
	
	public int getBillingID(){
		return this.billingID;
	}
}
