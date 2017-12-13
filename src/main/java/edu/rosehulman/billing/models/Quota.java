package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import edu.rosehulman.billing.ObjectIdDeserializer;
@Entity("quota")
public class Quota {
	@Id
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	@JsonIgnore
	private ObjectId id;
	@Property
	@JsonProperty("name")
	private String name;
	@Property
	@JsonProperty("type")
	private String type;
	@Property
	@JsonProperty("quotaId")
	private String quotaId;
	@Reference
	@JsonProperty("tiers")
	private List<Tier> tiers = new ArrayList<Tier>();
	@Reference
	@JsonIgnore
	private Product product;
	@Reference
	@JsonIgnore
	private Partner partner;

	public Quota() {

	}

	public Quota(String quotaId, String name, String type) {
		this.id = new ObjectId();
		this.quotaId = quotaId;
		this.name = name;
		this.type = type;
	}

	@JsonIgnore
	public String getId() {
		return this.quotaId;
	}

	public void setId(String id) {
		this.quotaId = id;
	}
	
	public void setPartner(Partner partner){
		this.partner = partner;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}

	@JsonIgnore
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonIgnore
	public Partner getPartner() {
		return this.partner;
	}

	@JsonIgnore
	public Product getProduct() {
		return this.product;
	}

	public String getType() {
		return this.type;
	}

	@JsonIgnore
	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}

	public void addTiers(Tier tier) {
		this.tiers.add(tier);
	}

	public void removeTiers(Tier tier) {
		this.tiers.remove(tier);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quota: " + this.quotaId + "\n");
		builder.append("Name: " + name + "\n");
		builder.append("Type: " + type + "\n");
		builder.append("Tiers: ");
		for (Tier t : tiers) {
			builder.append(t.toString());
		}
		return builder.toString();
	}

	@JsonIgnore
	public ObjectId getObjectId() {
		return this.id;
	}
}
