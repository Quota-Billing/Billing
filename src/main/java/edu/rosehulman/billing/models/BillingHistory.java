package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("BillingHistory")
public class BillingHistory {
	@Id
	private ObjectId id;
	@Property
	private int billinghistoryId;
	@Reference
	private HashMap<Integer, Billing> billing;
	@Property
	private String time_stamp;
	@Property
	private Double fee;

	public BillingHistory(int id) {
		this.id = new ObjectId();
		this.billinghistoryId = id;
		billing = new HashMap<Integer, Billing>();
		time_stamp = "";
		fee = 0.0;
	}
	
	public BillingHistory(int id, String time_stamp, double fee) {
		this.id = new ObjectId();
		this.billinghistoryId = id;
		billing = new HashMap<Integer, Billing>();
		this.time_stamp = time_stamp;
		this.fee = fee;
	}

	public void setBillingHistoryId(int id) {
		this.billinghistoryId = id;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public void setTimeStamp(String st) {
		this.time_stamp = st;
	}

	public int getBillingHistoryId() {
		return this.billinghistoryId;
	}

	public double getFee() {
		return this.fee;
	}

	public String getTimeStamp() {
		return this.time_stamp;
	}

	public void UpdateExistingBilling(Billing newBill) {
		int index = newBill.getBillingID();
		this.billing.put(index, newBill);
	}

	public void deleteBilling(Billing oldBill) {
		int index = oldBill.getBillingID();
		this.billing.remove(index);
	}

	public void deleteBilling(int billingid) {
		this.billing.remove(billingid);
	}

	public boolean findBilling(Billing bill) {
		return this.billing.containsKey(bill.getBillingID());
	}

	public boolean findBilling(int billingid) {
		return this.billing.keySet().contains(billingid);
	}

	public void addBilling(Billing bl) {
		this.billing.put(bl.getBillingID(), bl);
	}

	public ArrayList<Billing> getBillingList() {
		ArrayList<Billing> bill = new ArrayList<Billing>();
		for (int i : this.billing.keySet()) {
			bill.add(this.billing.get(i));
		}
		return bill;
	}

	public Set<Integer> getBillingIdList() {
		return this.billing.keySet();
	}

	public String toString() {
		String st = "Billing History: " + this.billinghistoryId + "\n";
		for (int i : this.billing.keySet()) {
			st += "Billing: " + i + " " + this.billing.get(i) + "\n";
		}
		return st;
	}

}
