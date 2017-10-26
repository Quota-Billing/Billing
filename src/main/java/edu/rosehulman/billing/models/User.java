package edu.rosehulman.billing.models;
import org.bson.types.ObjectId;

public final class User {
	private ObjectId id;
	private ObjectId productId;
	private ObjectId partnerId;
	
	public User(){
		
	}
	
	public User(ObjectId id, ObjectId productId, ObjectId partnerId){
		this.id = id;
		this.productId = productId;
		this.partnerId = partnerId;
	}
	
	public ObjectId getId(){
		return this.id;
	}
	
	public ObjectId getProductId(){
		return this.productId;
	}
	
	public ObjectId getPartnerId(){
		return this.partnerId;
	}
}
