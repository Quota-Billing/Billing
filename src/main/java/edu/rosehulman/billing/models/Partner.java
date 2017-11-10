package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
@Entity("partner")
public class Partner {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Reference
	private List<Product> products;
	@Property
	private String partnerId;
	@Property
	private String apikey;
	@Property
	private String password;

	public Partner() {
		this.products = new ArrayList<Product>();
	}

	public Partner(String partnerId, String name, String apikey) {
		this.id = new ObjectId();
		this.partnerId = partnerId;
		this.name = name;
		this.apikey = apikey;
		this.products = new ArrayList<Product>();
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	public Product getProduct(String productId) {
		for(Product p: this.products){
			if(p.getId().equals(productId)){
				return p;
			}
		}
		return null;
	}

	public List<Product> getAllProducts(){
		return this.products;
	}
	
	public void removeProduct(Product product) {
		this.products.remove(product);
	}

	public void setId(String id) {
		this.partnerId = id;
	}

	public String getId() {
		return this.partnerId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getApiKey() {
		return this.apikey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner: " + this.partnerId + "\n");
		for (Product p : this.products) {
			builder.append(p.getId().toString());
		}
		return builder.toString();
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
}

