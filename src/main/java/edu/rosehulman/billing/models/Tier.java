package edu.rosehulman.billing.models;

public class Tier {
	private String id;
	private String name;
	private int max; // TODO: This might need to be changed to BigInt or
						// BigDouble
	private int value;
	private double price; // TODO: Not a good idea to save price in a double
							// format
	
	public Tier(String id, String name, int max, int value, int price) {
		this.id = id;
		this.name = name;
		this.max = max;
		this.value = value;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}