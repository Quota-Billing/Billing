package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.User;
import edu.rosehulman.billing.Database;

public class UserDatastore {
	Datastore datastore;

	public UserDatastore(Datastore ds) {
		this.datastore = ds;
	}

	public String addUser(String id, String productId, String partnerId) {
		try {
			List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId)
					.asList();
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
			datastore.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public User getUser(String partnerId, String productId, String userId) {
		try {
			List<User> users = datastore.createQuery(User.class).asList();
			if (users.size() == 0) {
				System.out.println("no users");
			}
			for (User u : users) {
				if (u.getPartner().getId().equals(partnerId) && u.getProduct().getId().equals(productId)
						&& u.getId().equals(userId)) {
					return u;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	public String updateUser(User user) {
		Query<User> query = this.datastore.createQuery(User.class).field("id").equal(user.getObjectId());
		List<User> list = query.asList();
		UpdateOperations<User> op = this.datastore.createUpdateOperations(User.class).set("tier", user.getTier());
		this.datastore.update(query, op);
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

}
