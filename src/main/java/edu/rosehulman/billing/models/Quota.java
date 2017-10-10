package edu.rosehulman.billing.models;

import java.util.List;

public class Quota {
	private int id;
	private String name;
	private String type;
	private List<Tier> tiers;
	
	public Quota(int id, String name, String type){
		this.id = id;
		this.name = name;
		this.type=type;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quota: "  + id + "\n");
		for(Tier t: tiers) {
			builder.append(t.toString());
		}
		return builder.toString();
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getType(){
		return this.type;
	}
}
