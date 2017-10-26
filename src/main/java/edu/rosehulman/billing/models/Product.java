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
	private HashMap<ObjectId, Quota> quotas = new HashMap<ObjectId, Quota>();

	public Product() {

	}

	public Product(ObjectId id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getId() {
		return this.id;
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

	public HashMap<ObjectId, Quota> getQuotas() {
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
		builder.append("Name: " + name + "\n");
		builder.append("Quotas: ");
		for (ObjectId id : this.quotas.keySet()) {
			builder.append(this.quotas.get(id).toString());
		}
		return builder.toString();
	}

}
