package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("Billing")
public class Billing {
	@Id
	private ObjectId id;
	@Property
	private int billingID;
	@Reference
	private int userID;
	@Property
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
	
	public void setBillingID(int id) {
		this.billingID = id;
	}
	
	public void setUserID(int id) {
		this.userID = id;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String toString() {
		return "BillingId: " + this.billingID + "\n UserId: " + this.userID + "\n Plan: "+ this.plan +"\n";
	}
}
