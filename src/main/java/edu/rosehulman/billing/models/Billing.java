package edu.rosehulman.billing.models;

import java.util.ArrayList;

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
	@Reference
	private ArrayList<BillingHistory> billinghistory;
	
	public Billing(int bid, int uid, String plan){
		this.billingID = bid;
		this.userID = uid;
		this.plan = plan;
		this.billinghistory = new ArrayList<BillingHistory>();
	}
	
	public int getUserId(){
		return this.userID;
	}
	
	public int getBillingID(){
		return this.billingID;
	}
	
	public String getPlan(){
		return this.plan;
	}
	
	public void setBillingID(int id) {
		int index = this.billingID;
		BillingHistory bh = this.billinghistory.get(index);
		bh.UpdateExistingBilling(this, id);
		this.billingID = id;
	}
	
	public void setUserID(int id) {
		this.userID = id;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public void addToBillingHistory(BillingHistory e) {
		this.billinghistory.add(e);
	}
	
	public String toString() {
		return "BillingId: " + this.billingID + "\nUserId: " + this.userID + "\nPlan: "+ this.plan +"\n";
	}
}
