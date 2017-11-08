package edu.rosehulman.billing;

import org.bson.Document;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

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

	public void addPartner(String partnerId) {

	}

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

	public static void main(String args[]) {
		UpdateUser();
	}

	public static void UpdateUser() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("user");
		MongoCollection<Document> collectionbilling = database.getCollection("tier");
		// MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			String userid = (String) cur.get("id");
			Object product = cur.get("product");
			Object partner = cur.get("partner");
			for (Document cur2 : collectionbilling.find()) {
				if (!cur.equals(cur2)) {
					// add user here
					Database.getInstance().addUser(userid, product.toString(), partner.toString());
				}
			}

		}
	}

	public void UpdatePartner() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("partner");
		MongoCollection<Document> collectionbilling = database.getCollection("tier");
		// MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			String partnerId = (String) cur.get("partnerId");
			String name = (String) cur.get("name");
			String apikey = (String) cur.get("apikey");
			String password = (String) cur.get("password");
			Object products = cur.get("products");
			for (Document cur2 : collectionbilling.find()) {
				if (!cur.equals(cur2)) {
					// add partner here
					Database.getInstance().addPartner(partnerId, name, apikey, password);
				}
			}
		}
	}

	public void UpdateProduct() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("product");
		MongoCollection<Document> collectionbilling = database.getCollection("tier");
		// MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			String productId = (String) cur.get("productId");
			String name = (String) cur.get("name");
			Object quotas = cur.get("quotas");
			for (Document cur2 : collectionbilling.find()) {
				if (!cur.equals(cur2)) {
					// add Product here
					Database.getInstance().addProductToPartner(partnerId, name, productId);
				}
			}
		}
	}

	public void UpdateQuota() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("quota");
		MongoCollection<Document> collectionbilling = database.getCollection("tier");
		// MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			String quotaId = (String) cur.get("quotaId");
			String name = (String) cur.get("name");
			String type = (String) cur.get("type");
			Object product = cur.get("product");
			Object partner = cur.get("partner");
			Object tiers = cur.get("tiers");
			System.out.println(tiers);
			for (Document cur2 : collectionbilling.find()) {
				if (!cur.equals(cur2)) {
					// add Quota here
					Database.getInstance().addQuota(partner.toString(), product.toString(), quotaId, name, type);
				}
			}
		}
	}

	public void UpdateTier() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("tier");
		MongoCollection<Document> collectionbilling = database.getCollection("tier");
		// MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			String tierId = (String) cur.get("tierId");
			Double price = (Double) cur.get("price");
			String name = (String) cur.get("name");
			Integer max = (Integer) cur.get("max");
			Integer value = (Integer) cur.get("value");
			Object quota = (Object) cur.get("quota");
			Object product = (Object) cur.get("product");
			Object partner = (Object) cur.get("partner");
			for (Document cur2 : collectionbilling.find()) {
				if (!cur.equals(cur2)) {
					// add tier here
					Database.getInstance().addTier(partner.toString(), product.toString(), quota.toString(), tierId,
							name, max.toString(), price.toString());
				}
			}
		}
	}

}
