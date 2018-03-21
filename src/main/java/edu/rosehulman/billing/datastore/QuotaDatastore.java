package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public void deleteQuota(Quota quota){
		try {
			this.datastore.delete(quota);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
