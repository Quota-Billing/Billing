package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("tier")
public class Tier {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private String tierId;
	@Property
	private int max; 
	@Property
	private int value;
	@Property
	private double price;
	@Reference
	private Partner partner;
	@Reference
	private Product product;
	@Reference
	private Quota quota;

	public Tier() {
		
	}
	
	public Tier(String id, String name, int max, double price) {
		this.id = new ObjectId();
		this.tierId = id;
		this.name = name;
		this.max = max;
		this.price = price;
	}

	public void setPartner(Partner partner){
		this.partner = partner;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public void setQuota(Quota quota){
		this.quota = quota;
	}
	
	public String getId() {
		return this.tierId;
	}

	public void setId(String id) {
		this.tierId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Tier: " + this.tierId + "\n";
	}
}