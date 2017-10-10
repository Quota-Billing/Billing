package edu.rosehulman.billing.models;

import java.util.HashMap;

public class Partner {
	private int id;
	private String name;
	private HashMap<Integer, Product> products;

	public Partner(int partnerId, String name){
		this.id = partnerId;
		this.name = name;
		this.products = new HashMap<Integer, Product>();
	}
	
	public void addProduct(Product product){
		this.products.put(product.getId(), product);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner: "  + id + "\n");
		for(Integer id : this.products.keySet()) {
			builder.append(this.products.get(id).toString());
		}
		return builder.toString();
		
	}
	
	public Product getProduct(int productId) {
		return this.products.get(productId);
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
}
