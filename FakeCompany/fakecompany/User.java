package fakecompany;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;

public class User {
	
	private ObjectId id;
	
	private String userId;
	
	private String password;
	
	private String paymentToken;

	public User() {

	}

	public User(String id) {
		this.id = new ObjectId();
		this.userId = id;

	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}

	public String getId() {
		return this.userId;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPaymentToken() {
		return this.paymentToken;
	}

	public String toString() {
		return "User: " + this.userId + "\nPassword: " + this.password + "\nPaymentToken: "
				+ this.paymentToken;
	}

}
