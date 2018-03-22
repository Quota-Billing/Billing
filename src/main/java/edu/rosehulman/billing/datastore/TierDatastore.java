package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.models.Quota;
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
			System.out.println(t.getId());
			System.out.println(t.getQuota().getId().toString());
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
			Query<Quota> query = this.datastore.createQuery(Quota.class).field("id")
					.equal(tier.getQuota().getObjectId());
			UpdateOperations<Quota> op = this.datastore.createUpdateOperations(Quota.class).push("tiers", tier);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void deleteTier(Tier tier) {
		try {
			this.datastore.delete(tier);
			Query<Quota> query = this.datastore.createQuery(Quota.class).field("id")
					.equal(tier.getQuota().getObjectId());
			UpdateOperations<Quota> op = this.datastore.createUpdateOperations(Quota.class).removeAll("tiers", tier);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
