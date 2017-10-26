package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class BillingHistory {

	private int billinghistoryId;
	private HashMap<Integer, Billing> billing;
	private String time_stamp;
	private Double fee;

	public BillingHistory(int id) {
		this.billinghistoryId = id;
		billing = new HashMap<Integer, Billing>();
		time_stamp = "";
		fee = 0.0;
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

}
