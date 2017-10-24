package edu.rosehulman.billing.models;

import java.util.HashMap;

public class Partner {
	private int id;
	private String name;
	private HashMap<Integer, Product> products = new HashMap<Integer, Product>();

	public Partner() {

	}

	public Partner(int partnerId, String name) {
		this.id = partnerId;
		this.name = name;
	}

	public void addProduct(Product product) {
		this.products.put(product.getId(), product);
	}

	public Product getProduct(int productId) {
		return this.products.get(productId);
	}

	public void removeProduct(Product product) {
		this.products.remove(product.getId());
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner: " + id + "\n");
		for (Integer id : this.products.keySet()) {
			builder.append(this.products.get(id).toString());
		}
		return builder.toString();
	}
}
