package edu.rosehulman.billing.datastore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.BillingHistory;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.User;

public class BillingDatastore {
	private Datastore datastore;
	
	public BillingDatastore(Datastore datastore){
		this.datastore = datastore;
	}
	
	public Billing getBilling(String userId, String partnerId, String productId){
		List<Billing> billings = datastore.createQuery(Billing.class).asList();
		if(billings.size() == 0){
			System.out.println("wrong billing/partner/product ids");
			return null;
		}
		
		for(Billing b: billings){
			User user = b.getUser();
			if(user.getId().equals(userId))
				if(user.getPartner().getId().equals(partnerId) && user.getProduct().getId().equals(productId)){
					return b;
				} 
		}
		return null;
	}
	
	public void addBilling(String userID, String partnerId, String productId, String plan, double fee){
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
			}
			List<User> users = this.datastore.createQuery(User.class).field("userId").equal(userID).field("product")
					.equal(product).field("partner").equal(partner).asList();
			if (users == null) {
				System.out.println("wrong userId");
			}
			User user = users.get(0);
			Billing bill = new Billing(user, plan, fee);
			this.datastore.save(bill);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
	}
	
	public void deleteBilling(String userID, String partnerId, String productId){
		try {
			Query<Billing> query1 = datastore.createQuery(Billing.class).field("partnerId").equal(partnerId)
					.field("userId").equal(userID).field("productId").equal(productId);
			
			this.datastore.findAndDelete(query1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
