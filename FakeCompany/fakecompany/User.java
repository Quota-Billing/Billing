package fakecompany;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;


public class User {
	@Id
	private ObjectId id;
	@Property("userId")
	private String userId;
	@Property("password")
	private String password;
	@Property("paymentToken")
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
