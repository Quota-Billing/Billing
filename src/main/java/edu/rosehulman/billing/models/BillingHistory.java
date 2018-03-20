package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("billingHistory")
public class BillingHistory {
	@Id
	private ObjectId id;
	@Reference
	private List<Billing> billing;
	@Property
	private String time_stamp;
	@Reference
	private User user;

	public BillingHistory() {
		billing = new ArrayList<Billing>();
	}

	public BillingHistory(String time_stamp, User user) {
		this.id = new ObjectId();
		billing = new ArrayList<Billing>();
		this.time_stamp = time_stamp;
		this.user = user;
	}

	public void setTimeStamp(String st) {
		this.time_stamp = st;
	}

	public ObjectId getBillingHistoryId() {
		return this.id;
	}

	public String getTimeStamp() {
		return this.time_stamp;
	}

	public void deleteBilling(Billing oldBill) {
		Billing delete = null;
		for (Billing b : this.billing) {
			if (b.getId() == oldBill.getId())
				delete = b;
		}
		if (delete != null) {
			this.billing.remove(delete);
		}
	}

	public Billing findBilling(Billing bill) {
		for (Billing b : this.billing) {
			if (b.getUserId().equals(bill.getUserId()))
				return b;
		}
		return null;
	}

	public void addBilling(Billing bl) {
		this.billing.add(bl);
	}

	public List<Billing> getBillingList() {
		return this.billing;
	}
	
	public User getUser(){
		return this.user;
	}

	public String toString() {
		String st = "Billing History: " + this.id + "\n";
		for (Billing i : this.billing) {
			st += i.toString();
		}
		return st;
	}

}
