package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
@Entity("quota")
public class Quota {
	@Id
	private ObjectId id;
	@Property
	private String name;
	@Property
	private String type;
	@Property
	private List<Tier> tiers = new ArrayList<Tier>();

	public Quota() {

	}

	public Quota(ObjectId id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public ObjectId getId() {
		return this.id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

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
		builder.append("Quota: " + id + "\n");
		builder.append("Name: " + name + "\n");
		builder.append("Type: " + type + "\n");
		builder.append("Tiers: ");
		for (Tier t : tiers) {
			builder.append(t.toString());
		}
		return builder.toString();
	}
}
