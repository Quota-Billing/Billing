package edu.rosehulman.billing.models;

import java.util.ArrayList;

public class BillingHistory {

	private int billinghistoryId;
	private ArrayList<Billing> billing;
	private ArrayList<Integer> billingId;
	private ArrayList<String> time_stamp;
	private ArrayList<Double> fee;
	

	public BillingHistory(int id) {
		this.billinghistoryId = id;
		billingId = new ArrayList<Integer>();
		time_stamp = new ArrayList<String>();
		fee = new ArrayList<Double>();
	}
	
	public void setBillingHistoryId(int id) {
		this.billinghistoryId = id;
	}

	public int getBillingHistoryId() {
		return this.billinghistoryId;
	}

	public void addBilling(int billingid) {
		this.billingId.add(billingid);
	}

	public double getFee(int billingid) {
		return this.fee.get(this.billingId.indexOf(billingid));
	}

	public String getTimeStamp(int billingid) {
		return this.time_stamp.get(this.billingId.indexOf(billingid));
	}
	
	public void addBilling(Billing bl, String timestamp, double fee) {
		this.billing.add(bl);
		this.billingId.add(bl.getBillingID());
		this.time_stamp.add(timestamp);
		this.fee.add(fee);
	}
	
}
