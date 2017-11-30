package edu.rosehulman.billing;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.mapping.DefaultCreator;
import org.mongodb.morphia.query.Query;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;

// this is a client connecting to sharedservice so we can pull updates
public class SharedClient {
	private static SharedClient instance;

	private SharedClient() {
	}

	public static synchronized SharedClient getInstance() {
		if (instance == null) {
			instance = new SharedClient();
		}
		return instance;
	}

	// public void addUser(String partnerId, String productId, String quotaId,
	// String userId) throws Exception {
	// // Send Billing the user
	//
	// HttpResponse<String> response = Unirest.post("http://localhost:8081/" +
	// "partner/{partnerId}/product/{productId}quota/{quotaId}/user/{userId}")
	// .routeParam("partnerId", partnerId)
	// .routeParam("productId", quotaId)
	// .routeParam("quotaId", quotaId)
	// .routeParam("userId", userId)
	// .asString();
	//
	// if (response.getStatus() != 200) {
	// System.out.println(response.getStatus());
	// throw new Exception();
	// }
	// }

	public String addUserInfo(String partnerId, String productId, String userId) throws Exception {
		// HttpResponse<JsonNode> response = Unirest.get("http://localhost:8085/getUser"
		// + "partner/{partnerId}/product/{productId}quota/{quotaId}/user/{userId}")
		// .routeParam("partnerId", partnerId)
		// .routeParam("productId", productId)
		// .routeParam("userId", userId)
		// .asJson();
		// if (response.getStatus() != 200) {
		// System.out.println(response.getStatus());
		// throw new Exception();
		// }
		// JsonNode result = response.getBody();
		// System.out.println(result.toString());
		// JSONObject ids = result.getObject();
		// will use for adding other models.
		// Database.getInstance().addUser(ids.getString("partnerId"),
		// ids.getString("productId"), ids.getString("userId"));
		System.out.println(productId);
		Database.getInstance().addUser(partnerId, productId, userId);
		return "ok";
	}

	public void UpdatePartner(String partnerId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		// Do set up here
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia.mapPackage("edu.rosehulman.quotabillingshare");
		Datastore datastore = morphia.createDatastore(mongoClient, "quotabillingshare");

		Query<Partner> queryP = datastore.createQuery(Partner.class);
		List<Partner> partners = queryP.asList();

		MongoClient mongoClient2 = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia2 = new Morphia();
		// Do set up here.
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia2.mapPackage("edu.rosehulman.billingpart");
		Datastore datastoreBilling = morphia2.createDatastore(mongoClient2, "billingpart");

		Query<Partner> queryP2 = datastoreBilling.createQuery(Partner.class);
		List<Partner> partnersold = queryP2.asList();

		for (Partner partner : partners) {
			if (partner.getId().equals(partnerId)) {
				Database.getInstance().addPartner(partner.getId(), partner.getName(), partner.getApiKey(),
						partner.getPassword());
				List<Product> pro = partner.getAllProducts();
				for (Product k : pro)
					Database.getInstance().addProductToPartner(partner.getId(), k.getName(), k.getId());
				break;
			}
		}
	}

	public void UpdateQuota(String QuotaId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		// Do set up here
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia.mapPackage("edu.rosehulman.quotabillingshare");
		Datastore datastore = morphia.createDatastore(mongoClient, "quotabillingshare");

		Query<Quota> queryQ = datastore.createQuery(Quota.class);
		List<Quota> quotas = queryQ.asList();

		MongoClient mongoClient2 = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia2 = new Morphia();
		// Do set up here.
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia2.mapPackage("edu.rosehulman.billingpart");
		Datastore datastoreBilling = morphia2.createDatastore(mongoClient2, "billingpart");

		Query<Quota> queryQ2 = datastoreBilling.createQuery(Quota.class);
		List<Quota> quotasold = queryQ2.asList();

		for (Quota quota : quotas) {
			if (quota.getId().equals(QuotaId)) {
				Database.getInstance().addQuota(quota.getPartner().getId(), quota.getProduct().getId(), quota.getId(),
						quota.getName(), quota.getType());
				break;
			}
		}
	}

	public void UpdateTier(String TierId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		// Do set up here
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia.mapPackage("edu.rosehulman.quotabillingshare");
		Datastore datastore = morphia.createDatastore(mongoClient, "quotabillingshare");

		Query<Tier> queryT = datastore.createQuery(Tier.class);
		List<Tier> tiers = queryT.asList();

		MongoClient mongoClient2 = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia2 = new Morphia();
		// Do set up here.
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia2.mapPackage("edu.rosehulman.billingpart");
		Datastore datastoreBilling = morphia2.createDatastore(mongoClient2, "billingpart");

		Query<Tier> queryT2 = datastoreBilling.createQuery(Tier.class);
		List<Tier> tiersold = queryT2.asList();

		for (Tier tier : tiers) {
			if (tier.getId().equals(TierId)) {
				Database.getInstance().addTier(tier.getPartner().getId(), tier.getProduct().getId(),
						tier.getQuota().getId(), tier.getId(), tier.getName(), tier.getMax() + "",
						tier.getPrice() + "");
				break;
			}
		}
	}

	public void Update() {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		// Do set up here
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia.mapPackage("edu.rosehulman.quotabillingshare");
		Datastore datastore = morphia.createDatastore(mongoClient, "quotabillingshare");

		Query<User> queryU = datastore.createQuery(User.class);
		List<User> users = queryU.asList();

		Query<Partner> queryP = datastore.createQuery(Partner.class);
		List<Partner> partners = queryP.asList();

		Query<Product> queryPr = datastore.createQuery(Product.class);
		List<Product> products = queryPr.asList();

		Query<Tier> queryT = datastore.createQuery(Tier.class);
		List<Tier> tiers = queryT.asList();

		Query<Quota> queryQ = datastore.createQuery(Quota.class);
		List<Quota> quotas = queryQ.asList();

		MongoClient mongoClient2 = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia2 = new Morphia();
		// Do set up here.
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia2.mapPackage("edu.rosehulman.billingpart");
		Datastore datastoreBilling = morphia2.createDatastore(mongoClient2, "billingpart");

		Query<User> queryU2 = datastoreBilling.createQuery(User.class);
		List<User> usersold = queryU2.asList();

		Query<Partner> queryP2 = datastoreBilling.createQuery(Partner.class);
		List<Partner> partnersold = queryP2.asList();

		Query<Product> queryPr2 = datastoreBilling.createQuery(Product.class);
		List<Product> productsold = queryPr2.asList();

		Query<Tier> queryT2 = datastoreBilling.createQuery(Tier.class);
		List<Tier> tiersold = queryT2.asList();

		Query<Quota> queryQ2 = datastoreBilling.createQuery(Quota.class);
		List<Quota> quotasold = queryQ2.asList();

		for (Partner partner : partners) {
			boolean flag = true;
			for (Partner partnerold : partnersold) {
				if (partner.getId().equals(partnerold.getId())) {
					flag = false;
				}
			}
			if (flag) {
				Database.getInstance().addPartner(partner.getId(), partner.getName(), partner.getApiKey(),
						partner.getPassword());
				List<Product> pro = partner.getAllProducts();
				for (Product k : pro)
					Database.getInstance().addProductToPartner(partner.getId(), k.getName(), k.getId());
			}
		}

		for (User user : users) {
			boolean flag = true;
			for (User userold : usersold) {
				if (user.getId().equals(userold.getId())
						&& user.getPartner().getId().equals(userold.getPartner().getId())) {
					flag = false;
				}
			}
			if (flag) {
				Database.getInstance().addUser(user.getId(), user.getProduct().getId(), user.getPartner().getId());
			}
		}

		for (Quota quota : quotas) {
			boolean flag = true;
			for (Quota quotaold : quotasold) {
				if (quota.getId().equals(quotaold.getId()) && quota.getName().equals(quotaold.getName())) {
					flag = false;
				}
			}
			if (flag) {
				Database.getInstance().addQuota(quota.getPartner().getId(), quota.getProduct().getId(), quota.getId(),
						quota.getName(), quota.getType());
			}
		}

		for (Tier tier : tiers) {
			boolean flag = true;
			for (Tier tierold : tiersold) {
				if (tier.getId().equals(tierold.getId()) && tier.getName().equals(tierold.getName())) {
					flag = false;
				}
			}
			if (flag) {
				Database.getInstance().addTier(tier.getPartner().getId(), tier.getProduct().getId(),
						tier.getQuota().getId(), tier.getId(), tier.getName(), tier.getMax() + "",
						tier.getPrice() + "");
			}
		}

	}

	public void UpdateProduct(String productId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		Morphia morphia = new Morphia();
		// Do set up here
		// morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
		// @Override
		// protected ClassLoader getClassLoaderForClass() {
		// return MongoBundleActivator.getBundleClassLoader();
		// }
		// });
		morphia.mapPackage("edu.rosehulman.quotabillingshare");
		Datastore datastore = morphia.createDatastore(mongoClient, "quotabillingshare");

		Query<Partner> queryP = datastore.createQuery(Partner.class);
		List<Partner> partners = queryP.asList();
		for (Partner partner : partners) {
			List<Product> pro = partner.getAllProducts();
			boolean flag = false;
			for (Product p : pro) {

				if (p.getId().equals(productId)) {
					Database.getInstance().addProductToPartner(partner.getId(), p.getName(), p.getId());
					flag = true;
					break;
				}
			}
			if (flag)
				break;
		}

	}

}
