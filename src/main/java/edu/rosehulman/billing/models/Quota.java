package edu.rosehulman.billing.models;

import java.util.ArrayList;
import java.util.List;

public class Quota {
	private int id;
	private String name;
	private String type;
	private List<Tier> tiers;

	public Quota(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.tiers = new ArrayList<Tier>();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quota: " + id + "\n");
		for (Tier t : tiers) {
			builder.append(t.toString());
		}
		return builder.toString();
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
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
}
