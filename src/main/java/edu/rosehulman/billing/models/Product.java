package edu.rosehulman.billing.models;

import java.util.HashMap;

public class Product {
	private int id;
	private String name;
	private HashMap<Integer, Quota> quotas = new HashMap<Integer, Quota>();
	
	public Product() {
		
	}

	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Quota getQuota(int quotaId) {
		return this.quotas.get(quotaId);
	}
	
	public HashMap<Integer, Quota> getQuotas() {
		return quotas;
	}
	
	public void removeQuota(Quota q) {
		this.quotas.remove(q.getId());
	}
	
	public void addQuota(Quota quota) {
		this.quotas.put(quota.getId(), quota);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product: " + id + "\n");
		builder.append("Name: "+name + "\n");
		builder.append("Quotas: ");
		for (Integer id : this.quotas.keySet()) {
			builder.append(this.quotas.get(id).toString());
		}
		return builder.toString();
	}

}
