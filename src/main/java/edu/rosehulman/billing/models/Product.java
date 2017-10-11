package edu.rosehulman.billing.models;

import java.util.HashMap;

public class Product {
	private int id;
	private String name;
	private HashMap<Integer, Quota> quotas;

	public Product(int id, String name) {
		this.id = id;
		this.name = name;
		quotas = new HashMap<Integer, Quota>();
	}

	public void addQuota(Quota quota) {
		this.quotas.put(quota.getId(), quota);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product: "  + id + "\n");
		for(Integer id : this.quotas.keySet()) {
			builder.append(this.quotas.get(id).toString());
		}
		return builder.toString();
	}
	public int getId() {
		return this.id;
	}
	public String getName(){
		return this.name;
	}

}
