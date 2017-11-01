package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;


@Entity("user")


public class User {
	@Id
	private ObjectId id;
	@Property("id")
	private String userId;
	@Reference
	private Product product;
	@Reference
	private Partner partner;
	
	public User(){
		
	}
	
	public User(String id){
		this.id = new ObjectId();
		this.userId = id;
		
	}
	
	public void setProduct(final Product product){
		this.product = product;
	}
	
	public void setPartner(final Partner partner){
		this.partner = partner;
	}
	
	public String getId(){
		return this.userId;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	public Partner getPartner(){
		return this.partner;
	}
}

