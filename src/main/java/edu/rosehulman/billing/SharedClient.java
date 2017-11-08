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
//		MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			int userid = (int) cur.get("id");
			String product = (String) cur.get("product");
			String partner = (String) cur.get("partner");
		}
	}
	
	public static void UpdatePartner() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("user");
//		MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			int partnerid = (int) cur.get("id");
			String product = (String) cur.get("product");
			String partner = (String) cur.get("partner");
		}
	}
	
	public static void UpdateProduct() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("user");
//		MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			int userid = (int) cur.get("id");
			String product = (String) cur.get("product");
			String partner = (String) cur.get("partner");
		}
	}
	
	public static void UpdateQuota() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("user");
//		MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			int userid = (int) cur.get("id");
			String product = (String) cur.get("product");
			String partner = (String) cur.get("partner");
		}
	}
	
	public static void UpdateTier() {
		MongoClient sharedMongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		MongoDatabase database = sharedMongoClient.getDatabase("quotabillingshare");
		MongoCollection<Document> collection = database.getCollection("user");
//		MongoCursor<Document> cursor = collection.find().iterator();
		for (Document cur : collection.find()) {
			int price = (int) cur.get("price");
			String name = (String) cur.get("name");
			int max = (int) cur.get("max");
			int value = (int) cur.get("value");
			String product = (String) cur.get("product");
			String partner = (String) cur.get("partner");
		}
	}

}
