package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
@Entity("product")
public class Product {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private String productId;
	@Reference
	private List<Quota> quotas = new ArrayList<Quota>();
	

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


	public List<Quota> getQuotas() {
		return quotas;
	}

	public void removeQuota(Quota q) {
		this.quotas.remove(q.getId());
	}

	public void addQuota(Quota quota) {
		this.quotas.add(quota);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product: " + this.productId + "\n");
		builder.append("Name: " + name + "\n");
		builder.append("Quotas: ");
		for (Quota q : this.quotas) {
			builder.append(q.toString());
		}
		return builder.toString();
	}

	public Quota getQuota(String quotaId) {
		for(Quota q: this.quotas){
			if(q.getId().equals(quotaId)){
				return q;
			}
		}
		return null;
	}

}
