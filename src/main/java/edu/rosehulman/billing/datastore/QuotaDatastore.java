package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;

public class QuotaDatastore {

	private Datastore datastore;
	
	public QuotaDatastore(Datastore datastore){
		this.datastore = datastore;
	}
	
	public Quota getQuota(String partnerId, String productId, String userId, String quotaId){
		List<Partner> partners = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.isEmpty()) {
			System.out.println("this partner doesn't exist");
			return null;
		}
		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		return quota;
	}
	
	public void addQuota(Quota quota){
		try {
			this.datastore.save(quota);
			Query<Product> query = this.datastore.createQuery(Product.class).field("id")
					.equal(quota.getProduct().getObjectId());
			UpdateOperations<Product> op = this.datastore.createUpdateOperations(Product.class).push("quotas", quota);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void deleteQuota(Quota quota){
		try {
			this.datastore.delete(quota);
			Query<Product> query = this.datastore.createQuery(Product.class).field("id")
					.equal(quota.getProduct().getObjectId());
			UpdateOperations<Product> op = this.datastore.createUpdateOperations(Product.class).removeAll("quotas", quota);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
