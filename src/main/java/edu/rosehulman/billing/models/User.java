package edu.rosehulman.billing.models;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity("user")


public class User {
	@Property("id")
	private String id;
	@Reference
	private Product product;
	@Reference
	private Partner partner;
	
	public User(){
		
	}
	
	public User(String id, Product product, Partner partner){
		this.id = id;
		this.product = product;
		this.partner = partner;
	}
	
	public String getId(){
		return this.id;
	}
	
	public Product getProductId(){
		return this.product;
	}
	
	public Partner getPartnerId(){
		return this.partner;
	}
}
