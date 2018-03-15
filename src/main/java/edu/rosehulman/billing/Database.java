package edu.rosehulman.billing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.BillingHistory;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;

public class Database {
	private static Database instance;
	private MongoClient mongoClient;
	private Datastore datastore;

	// create only one instance of mongoclient and not closing it until
	// application exits
	private Database() {
		this.mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia = new Morphia();
		morphia.mapPackage("edu.rosehulman.billingpart");
		this.datastore = morphia.createDatastore(this.mongoClient, "billingpart");

	}

	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	// add a partner
	public String addPartner(String partnerId, String name, String apiKey, String password) {
		try {
			Partner partner = new Partner(partnerId, name, apiKey);
			partner.setPassword(password);
			this.datastore.save(partner);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}
	
	public User getUser(String partnerId, String productId, String userId){
		try {
			List<User> users = datastore.createQuery(User.class).asList();
			if(users.size() == 0){
				System.out.println("no users");
			}
			for(User u: users){
				if(u.getPartner().getId().equals(partnerId)
						&& u.getProduct().getId().equals(productId)
						&& u.getId().equals(userId)){
					return u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
	
	public String updateUser(User user){
		Query<User> query = this.datastore.createQuery(User.class).field("id").equal(user.getObjectId());
		List<User> list = query.asList();
		UpdateOperations<User> op = this.datastore.createUpdateOperations(User.class).set("tier", user.getTier());
		this.datastore.update(query, op);
		return "ok";
	}
	
	public String updatePartner(Partner partner){
		Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
		List<Partner> list = query.asList();
		System.out.println("#partner: "+list.size());
		System.out.println(partner.getWebhook());
		UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).set("webhook",partner.getWebhook());
		this.datastore.update(query, op);
		return "ok";
	}

	public String addQuota(String partnerId, String productId, String quotaId, String name, String type) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			Quota quota = new Quota(quotaId, name, type);
			quota.setPartner(partner);
			quota.setProduct(product);
			this.datastore.save(quota);
			Query<Product> query = this.datastore.createQuery(Product.class).field("id").equal(product.getObjectId());
			UpdateOperations<Product> op = this.datastore.createUpdateOperations(Product.class).push("quotas", quota);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	// add a simple user by referencing a product and a partner
	public String addUser(String id, String productId, String partnerId) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			User user = new User(id);
			user.setPartner(partner);
			user.setProduct(product);
			this.datastore.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	// adding product to a specific partner, in mongoDB the product will be
	// saved by reference and its ID
	public String addProductToPartner(String partnerId, String name, String productId) {
		try {
			Partner partner = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList().get(0);
			Product product = new Product(productId, name);
			partner.addProduct(product);
			this.datastore.save(product);
			Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId);
			UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).push("products",
					product);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addTier(String partnerId, String productId, String quotaId, String tierId, String name, String max,
			String price, String graceExtra) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			Quota quota = product.getQuota(quotaId);
			if (quota == null) {
				System.out.println("wrong quotaId");
				return "Wrong quotaId";
			}
			Tier tier = new Tier(quotaId, name, Integer.valueOf(max), Double.valueOf(price), Integer.valueOf(graceExtra));
			tier.setPartner(partner);
			tier.setProduct(product);
			tier.setQuota(quota);
			this.datastore.save(tier);
			Query<Quota> query = this.datastore.createQuery(Quota.class).field("id").equal(quota.getObjectId());
			UpdateOperations<Quota> op = this.datastore.createUpdateOperations(Quota.class).push("tiers", tier);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}


	public String addBilling(String userID, String partnerId, String productId, String plan, double fee) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			List<User> users = this.datastore.createQuery(User.class).field("userId").equal(userID).field("product")
					.equal(product).field("partner").equal(partner).asList();
			if (users == null) {
				System.out.println("wrong userId");
				return "Wrong userId";
			}
			User user = users.get(0);
			Billing bill = new Billing(user, plan, fee);
			this.datastore.save(bill);
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return "ok";
	}

	public String getPartnerBillingInfo(String partnerId, String productId, String userId) {

		try {
			List<Partner> partners = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId)
					.asList();
			if (partners.isEmpty()) {
				System.out.println("this partner doesn't exist");
				return "wrong partner ID";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			List<User> users = this.datastore.createQuery(User.class).field("userId").equal(userId).field("product")
					.equal(product).field("partner").equal(partner).asList();
			if (users == null) {
				System.out.println("wrong userId");
				return "Wrong userId";
			}
			User user = users.get(0);
			String partnerName = partner.getName();
			String productName = product.getName();
			StringBuilder builder = new StringBuilder("Partner: " + partnerId + " ");
			builder.append(partnerName + "\n");
			builder.append("Product: " + productId + " " + productName + "\n");
			builder.append("User: " + user.getId() + "\n");
			return builder.toString();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		return null;
	}

	public boolean deleteUser(String partnerId, String productId, String userId) {
		final Query<User> deleteQuery = datastore.createQuery(User.class).field("userId").equal(userId);
		List<User> results = deleteQuery.asList();

		for (User u : results) {
			// TODO can/should we ever delete multiple?
			if (u.getPartner().getId().equals(partnerId) && u.getProduct().getId().equals(productId)) {
				datastore.delete(u);
				return true;
			}
		}
		return false;
	}

	public Partner getPartner(String partnerId) {
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		return partner;
	}

	public Tier getTier(String partnerId, String productId, String quotaId) {
		List<Partner> partners = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.isEmpty()) {
			System.out.println("this partner doesn't exist");
			return null;
		}
		Partner partner = partners.get(0);
		Product product = partner.getProduct(productId);
		Quota quota = product.getQuota(quotaId);
		final Query<Tier> query = datastore.createQuery(Tier.class).field("product").equal(product).field("partner")
				.equal(partner).filter("quota", quota);
		List<Tier> results = query.asList();
		if (results.size() == 0) {
			System.out.println("cant find such tier");
			return null;
		}
		return results.get(0);
	}
   //Fakecompany
//	public Tier getTier(String partnerId, String productId, String quotaId) {
//		List<Partner> partners = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId)
//				.asList();
//		if (partners.isEmpty()) {
//			System.out.println("this partner doesn't exist");
//			return null;
//		}
//		Partner partner = partners.get(0);
//		Product product = partner.getProduct(productId);
//		Quota quota = product.getQuota(quotaId);
//		final Query<Tier> query = datastore.createQuery(Tier.class).field("product").equal(product).field("partner").equal(partner).filter("quota", quota);
//		List<Tier> results = query.asList();
//		if(results.size() == 0){
//			System.out.println("cant find such tier");
//			return null;
//		}
//		return results.get(0);
//	}
	
	//master
	public BillingHistory getBillinghistory(String timestamp) {
		List<BillingHistory> histories = datastore.createQuery(BillingHistory.class).field("time_stamp").equal(timestamp).asList();
		if (histories.size() == 0) {
			System.out.println("wrong time stamp"+histories.size()); // debugging
			return null;
		}
		
		BillingHistory history = histories.get(0);
		
		System.out.println("flag here"+ history);
		return history;
	}



	// All these methods below are in test stage.
	public String addPartnerDirect(Partner partner) {
		try {
			this.datastore.save(partner);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String deletePartnerDirect(Partner partner) {
		try {
			Query<Partner> query1 = this.datastore.createQuery(Partner.class);
			query1.field("partnerId").equal(partner.getId());
			this.datastore.findAndDelete(query1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addProductDirect(Product product, Partner partner) {
		try {
			this.datastore.save(product);
			Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
			UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).push("products",
					product);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String deleteProductDirect(Product product, Partner partner) {
		try {
			Query<Product> query1 = this.datastore.createQuery(Product.class);
			query1.field("productId").equal(product.getId());
			this.datastore.findAndDelete(query1);
			
			Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
			UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).removeAll("products",
					product);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addQuotaDirect(Quota quota) {
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
		return "ok";
	}

	public String deleteQuotaDirect(Quota quota) {
		try {
			Query<Quota> query1 = this.datastore.createQuery(Quota.class);
			query1.field("quotaId").equal(quota.getId());
			this.datastore.findAndDelete(query1);
			
			Query<Product> query = this.datastore.createQuery(Product.class).field("id")
					.equal(quota.getProduct().getObjectId());
			UpdateOperations<Product> op = this.datastore.createUpdateOperations(Product.class).removeAll("quotas", quota);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addUserDirect(User user) {
		try {
			this.datastore.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String deleteUserDirect(User user) {
		try {
			Query<User> query1 = this.datastore.createQuery(User.class);
			query1.field("userId").equal(user.getId());
			this.datastore.findAndDelete(query1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addTierDirect(Tier tier) {
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
		return "ok";
	}

	public String deleteTierDirect(Tier tier) {
		try {
			Query<Tier> query1 = this.datastore.createQuery(Tier.class);
			query1.field("tierId").equal(tier.getId());
			this.datastore.findAndDelete(query1);
			
			Query<Quota> query = this.datastore.createQuery(Quota.class).field("id")
					.equal(tier.getQuota().getObjectId());
			UpdateOperations<Quota> op = this.datastore.createUpdateOperations(Quota.class).removeAll("tiers", tier);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String deleteBilling(String userID, String partnerId, String productId, String plan, double fee) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
			if (partners.size() == 0) {
				System.out.println("wrong partnerId"); // debugging
				return "Wrong partnerId";
			}
			Partner partner = partners.get(0);
			Product product = partner.getProduct(productId);
			if (product == null) {
				System.out.println("wrong productId"); // debugging
				return "Wrong productId";
			}
			List<User> users = this.datastore.createQuery(User.class).field("userId").equal(userID).field("product")
					.equal(product).field("partner").equal(partner).asList();
			if (users == null) {
				System.out.println("wrong userId");
				return "Wrong userId";
			}
			User user = users.get(0);
			Billing bill = new Billing(user, plan, fee);
			
			Query<Billing> query1 = this.datastore.createQuery(Billing.class);
			query1.field("userId").equal(user.getId());
			this.datastore.findAndDelete(query1);

		} catch (

		Exception e) {
			e.printStackTrace();
		} finally {
		}

		return "ok";
	}
	
	
	public BillingHistory getBillingHistory(String id) {
		List<BillingHistory> histories = datastore.createQuery(BillingHistory.class).field("id").equal(id).asList();
        if (histories.size() == 0) {
            System.out.println("wrong billinghistory id"); // debugging
            return null;
        }
        BillingHistory history = histories.get(0);
		return history;
	}
	
	public String deleteBillingHistory(String id) {
		try {
			Query<BillingHistory> query1 = this.datastore.createQuery(BillingHistory.class);
			query1.field("id").equal(id);
			this.datastore.findAndDelete(query1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

}