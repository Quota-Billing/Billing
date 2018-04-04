package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.rosehulman.billing.ObjectIdDeserializer;

@Entity("partner")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Partner {
	@Id
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	@JsonIgnore
	private ObjectId id;
	@Property
	@JsonProperty("name")
	private String name;
	@Reference
	@JsonProperty("products")
	private List<Product> products;
	@Property
	@JsonProperty("partnerId")
	private String partnerId;
	@Property
	@JsonProperty("apikey")
	private String apikey;
	@Property
	@JsonProperty("password")
	private String password;
	@Property
	@JsonProperty("webhook")
	private String webhook;

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

	public void setWebhook(String webhook) {
		this.webhook = webhook;
	}

	public String getWebhook() {
		return this.webhook;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}

	@JsonIgnore
	public Product getProduct(String productId) {
		for (Product p : this.products) {
			if (p.getId().equals(productId)) {
				return p;
			}
		}
		return null;
	}

	@JsonIgnore
	public List<Product> getAllProducts() {
		return this.products;
	}

	public void removeProduct(Product product) {
		this.products.remove(product);
	}

	public void setId(String id) {
		this.partnerId = id;
	}

	@JsonIgnore
	public String getId() {
		return this.partnerId;
	}

	@JsonIgnore
	public ObjectId getObjectId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getName() {
		return this.name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
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

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}
}
