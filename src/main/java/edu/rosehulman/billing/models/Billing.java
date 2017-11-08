package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("billing")
public class Billing {
	@Id
	private ObjectId id;
	@Reference
	private User user;
	@Property
	private String plan;
	@Property
	private Double fee;
	
	public Billing(){
		
	}
	
	public Billing(User user, String plan, Double fee){
		this.user = user;
		this.plan = plan;
		this.fee = fee;
	}
	
	public String getUserId(){
		return this.user.getId();
	}
	
	public double getFee(){
		return this.fee;
	}
	
	public void setFee(double fee){
		this.fee = fee;
	}
	
	public String getPlan(){
		return this.plan;
	}
	
	public ObjectId getId(){
		return this.id;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String toString() {
		return "BillingId: " + this.id + "\nUserId: " + this.user.getId() + "\nPlan: "+ this.plan +"\n";
	}

	public void setBillingID(int billingid2) {
		// TODO Auto-generated method stub
		
	}
}
