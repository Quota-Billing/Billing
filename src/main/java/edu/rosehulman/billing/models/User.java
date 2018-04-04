package edu.rosehulman.billing.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity("user")

public class User {
	@Id
	private ObjectId id;
	@Property("userId")
	private String userId;
	@Reference
	private Product product;
	@Reference
	private Partner partner;
	@Reference
	private Tier tier;

	public User() {

	}

	public User(String id) {
		this.id = new ObjectId();
		this.userId = id;

	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	@JsonIgnore
	public Tier getTier() {
		return this.tier;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	public void setPartner(final Partner partner) {
		this.partner = partner;
	}

	@JsonIgnore
	public String getId() {
		return this.userId;
	}

	@JsonIgnore
	public Product getProduct() {
		return this.product;
	}

	@JsonIgnore
	public Partner getPartner() {
		return this.partner;
	}

	public String toString() {
		return "User: " + this.userId + "\nProduct: " + this.product.toString() + "\nPartner: "
				+ this.partner.toString();
	}

	@JsonIgnore
	public ObjectId getObjectId() {
		return this.id;
	}
}
