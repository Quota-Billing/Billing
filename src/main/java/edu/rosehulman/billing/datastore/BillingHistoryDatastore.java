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
import edu.rosehulman.billing.models.User;

public class BillingHistoryDatastore {

	private Datastore datastore;
	
	public BillingHistoryDatastore(Datastore datastore){
		this.datastore = datastore;
	}
	
	public BillingHistory getBillingHistory(String id){
		List<BillingHistory> histories = datastore.createQuery(BillingHistory.class).field("id").equal(id).asList();
        if (histories.size() == 0) {
            System.out.println("wrong billinghistory id"); // debugging
            return null;
        }
        BillingHistory history = histories.get(0);
		return history;
	}
	
	public void addBillingHistory(String userID, String partnerId, String productId, Billing bill){
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
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		List<BillingHistory> histories = this.datastore.createQuery(BillingHistory.class).field("user").equal(user)
				.asList();
		if (histories.isEmpty()) {
			BillingHistory hist = new BillingHistory(timestamp, user);
			hist.addBilling(bill);
			this.datastore.save(hist);
		} else {
			BillingHistory history = histories.get(0);
			history.addBilling(bill);
			Query<BillingHistory> query = this.datastore.createQuery(BillingHistory.class).field("user")
					.equal(user);
			UpdateOperations<BillingHistory> op = this.datastore.createUpdateOperations(BillingHistory.class)
					.push("billing", bill);
			this.datastore.update(query, op);
		}
	}
	
	public void deleteBillingHistory(String userID, String partnerId, String productId){
		try {
			List<BillingHistory> list = datastore.createQuery(BillingHistory.class).field("userId").equal(userID).asList();
			for(BillingHistory b: list){
				if(b.getUser().getPartner().getId().equals(partnerId) && b.getUser().getProduct().getId().equals(productId)){
					this.datastore.delete(b); //need to be tested 
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
