package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity("tier")
public class Tier {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private String tierId;
	@Property
	private int max; // TODO: This might need to be changed to BigInt or
	@Property
	private int value;
	@Property
	private double price; // TODO: Not a good idea to save price in a double
							// format

	public Tier() {
		
	}
	
	public Tier(String id, String name, int max, int value, double price) {
		this.id = new ObjectId();
		this.tierId = id;
		this.name = name;
		this.max = max;
		this.value = value;
		this.price = price;
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