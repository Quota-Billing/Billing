package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;

import edu.rosehulman.billing.models.Tier;

public class TierDatastore {
	private Datastore datastore;

	public TierDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public Tier getTier(String partnerId, String productId, String userId, String quotaId, String tierId) {
		List<Tier> tiers = this.datastore.createQuery(Tier.class).field("tierId").equal(tierId).asList();
		if (tiers.isEmpty()) {
			System.out.println("this tierid doesn't exist");
			return null;
		}
		for (Tier t : tiers) {
			if (t.getPartner().getId().equals(partnerId) && t.getProduct().getId().equals(productId)
					&& t.getQuota().getId().equals(quotaId)) {
				return t;
			}
		}
		return null;
	}

	public void addTier(Tier tier) {
		try {
			this.datastore.save(tier);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void deleteQuota(Tier tier) {
		try {
			this.datastore.delete(tier);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
