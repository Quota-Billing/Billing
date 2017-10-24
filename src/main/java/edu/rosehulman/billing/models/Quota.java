package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

public class Quota {
	private int id;
	private String name;
	private String type;
	private List<Tier> tiers = new ArrayList<Tier>();

	public Quota() {

	}

	public Quota(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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
