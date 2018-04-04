package edu.rosehulman.billing;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.router.Routes;

// this is a client connecting to sharedservice so we can pull updates
public class SharedClient {
	private static SharedClient instance;
	private static MongoClient mongoClientshare;
	private static MongoClient mongoClientbilling;
	private static Morphia morphia;
	private Datastore datastore;

	private SharedClient() {
		mongoClientshare = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		mongoClientbilling = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		morphia = new Morphia();
		morphia.mapPackage("edu.rosehulman.billingpart");
		this.datastore = morphia.createDatastore(this.mongoClientshare, "billingpart");

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

	public Partner UpdatePartner(String partnerId) {
		HttpResponse<Partner> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR + "/partner/{partnerId}").routeParam("partnerId", partnerId)
					.asObject(Partner.class);
			Partner partner = response.getBody();
			return partner;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Quota UpdateQuota(String productId, String partnerId, String quotaId) {
		HttpResponse<Quota> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR + "/partner/{partnerId}/product/{productId}/quota/{quotaId}")
					.routeParam("partnerId", partnerId).routeParam("quotaId", quotaId)
					.routeParam("productId", productId).asObject(Quota.class);
			Quota quota = response.getBody();
			return quota;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product UpdateProduct(String productId, String partnerId) {

		HttpResponse<Product> response;
		try {
			response = Unirest.get(Routes.SHARED_ADDR + "/partner/{partnerId}/product/{productId}")
					.routeParam("partnerId", partnerId).routeParam("productId", productId).asObject(Product.class);
			Product product = response.getBody();
			return product;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Tier UpdateTier(String partnerId, String productId, String quotaId, String tierId) {
		HttpResponse<Tier> response;
		try {
			response = Unirest
					.get(Routes.SHARED_ADDR + "/partner/{partnerId}/product/{productId}/quota/{quotaId}/tier/{tierId}")
					.routeParam("partnerId", partnerId).routeParam("productId", productId)
					.routeParam("quotaId", quotaId).routeParam("tierId", tierId).asObject(Tier.class);
			Tier tier = response.getBody();
			System.out.println(tier == null);
			return tier;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

	// public void Update() {
	// // Do set up here
	// // morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
	// // @Override
	// // protected ClassLoader getClassLoaderForClass() {
	// // return MongoBundleActivator.getBundleClassLoader();
	// // }
	// // });
	// morphia.mapPackage("edu.rosehulman.quotabillingshare");
	// Datastore datastore = morphia.createDatastore(mongoClientshare,
	// "quotabillingshare");
	//
	// Query<User> queryU = datastore.createQuery(User.class);
	// List<User> users = queryU.asList();
	//
	// Query<Partner> queryP = datastore.createQuery(Partner.class);
	// List<Partner> partners = queryP.asList();
	//
	// Query<Product> queryPr = datastore.createQuery(Product.class);
	// List<Product> products = queryPr.asList();
	//
	// Query<Tier> queryT = datastore.createQuery(Tier.class);
	// List<Tier> tiers = queryT.asList();
	//
	// Query<Quota> queryQ = datastore.createQuery(Quota.class);
	// List<Quota> quotas = queryQ.asList();
	//
	// // Do set up here.
	// // morphia.getMapper().getOptions().setObjectFactory(new DefaultCreator() {
	// // @Override
	// // protected ClassLoader getClassLoaderForClass() {
	// // return MongoBundleActivator.getBundleClassLoader();
	// // }
	// // });
	// morphia.mapPackage("edu.rosehulman.billingpart");
	// Datastore datastoreBilling = morphia.createDatastore(mongoClientbilling,
	// "billingpart");
	//
	// Query<User> queryU2 = datastoreBilling.createQuery(User.class);
	// List<User> usersold = queryU2.asList();
	//
	// Query<Partner> queryP2 = datastoreBilling.createQuery(Partner.class);
	// List<Partner> partnersold = queryP2.asList();
	//
	// Query<Product> queryPr2 = datastoreBilling.createQuery(Product.class);
	// List<Product> productsold = queryPr2.asList();
	//
	// Query<Tier> queryT2 = datastoreBilling.createQuery(Tier.class);
	// List<Tier> tiersold = queryT2.asList();
	//
	// Query<Quota> queryQ2 = datastoreBilling.createQuery(Quota.class);
	// List<Quota> quotasold = queryQ2.asList();
	//
	// for (Partner partner : partners) {
	// boolean flag = true;
	// for (Partner partnerold : partnersold) {
	// if (partner.getId().equals(partnerold.getId())) {
	// flag = false;
	// }
	// }
	// if (flag) {
	// Database.getInstance().addPartner(partner.getId(), partner.getName(),
	// partner.getApiKey(),
	// partner.getPassword());
	// List<Product> pro = partner.getAllProducts();
	// for (Product k : pro)
	// Database.getInstance().addProductToPartner(partner.getId(), k.getName(),
	// k.getId());
	// }
	// }
	//
	// for (User user : users) {
	// boolean flag = true;
	// for (User userold : usersold) {
	// if (user.getId().equals(userold.getId())
	// && user.getPartner().getId().equals(userold.getPartner().getId())) {
	// flag = false;
	// }
	// }
	// if (flag) {
	// Database.getInstance().addUser(user.getId(), user.getProduct().getId(),
	// user.getPartner().getId());
	// }
	// }
	//
	// for (Quota quota : quotas) {
	// boolean flag = true;
	// for (Quota quotaold : quotasold) {
	// if (quota.getId().equals(quotaold.getId()) &&
	// quota.getName().equals(quotaold.getName())) {
	// flag = false;
	// }
	// }
	// if (flag) {
	// Database.getInstance().addQuota(quota.getPartner().getId(),
	// quota.getProduct().getId(), quota.getId(),
	// quota.getName(), quota.getType());
	// }
	// }
	//
	// for (Tier tier : tiers) {
	// boolean flag = true;
	// for (Tier tierold : tiersold) {
	// if (tier.getId().equals(tierold.getId()) &&
	// tier.getName().equals(tierold.getName())) {
	// flag = false;
	// }
	// }
	// if (flag) {
	// Database.getInstance().addTier(tier.getPartner().getId(),
	// tier.getProduct().getId(),
	// tier.getQuota().getId(), tier.getId(), tier.getName(), tier.getMax() + "",
	// tier.getPrice() + ""+tier.getgraceExtra());
	// }
	// }
	//
	// }

}
