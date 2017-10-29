package edu.rosehulman.billing.models;

import java.util.HashMap;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
@Entity("product")
public class Product {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private String productId;
	@Property
	private HashMap<String, Quota> quotas = new HashMap<String, Quota>();
	

	public Product() {

	}

	public Product(String productId, String name) {
		this.id = new ObjectId();
		this.productId = productId;
		this.name = name;
	}

	public void setId(String id) {
		this.productId = id;
	}

	public String getId() {
		return this.productId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Quota getQuota(ObjectId quotaId) {
		return this.quotas.get(quotaId);
	}

	public HashMap<String, Quota> getQuotas() {
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
		builder.append("Product: " + this.productId + "\n");
		builder.append("Name: " + name + "\n");
		builder.append("Quotas: ");
		for (String id : this.quotas.keySet()) {
			builder.append(this.quotas.get(id).toString());
		}
		return builder.toString();
	}

}
