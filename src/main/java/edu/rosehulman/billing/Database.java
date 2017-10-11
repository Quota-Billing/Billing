package edu.rosehulman.billing;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;

public class Database {
	private static Database instance;

	Document userDocument;
	MongoDatabase db;

	private Database() {

		MongoClientURI uri = new MongoClientURI("mongodb://admin:admin@ds159662.mlab.com:59662/srproj-test1");
		MongoClient client = new MongoClient(uri);
		MongoDatabase db = client.getDatabase(uri.getDatabase());

		MongoCollection<Document> testdb = db.getCollection("testFromVM");
		testdb.insertOne(userDocument);

	}

	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public void addUser(String partnerId, String productId, String userId) {

		userDocument = new Document("$set", new Document("_id", partnerId).append("products",
				new Document("_id", productId).append("users", new Document("_id", userId))));

		// partnerCollection.updateOne(and(eq("_id", partnerId), eq("products",
		// productId)), userDocument); // TODO This does not work, not using
		// lists
	}

	public Quota getQuotaInfo(String partnerId, String productId, String userId, String quotaId) {
		// BasicDBObject query = new BasicDBObject("_id",
		// partnerId).append("Products", new BasicDBObject("_id", productId)
		// .append("Users", new BasicDBObject("_id", userId).append("Quotas",
		// new BasicDBObject("_id", quotaId))));

		// Fake data first, will change later
		Quota quota = new Quota(0, "Data", "number");
		List<Tier> tiers = new ArrayList<Tier>();
		tiers.add(new Tier("1", "free", 200, 200, 0));
		tiers.add(new Tier("2", "premium", 1000, 0, 20));
		quota.setTiers(tiers);
		
		return quota;
	}

	public String getPartnerBillingInfo(String partnerId, String productId, String userId) {
//		BasicDBObject partnerQuery = new BasicDBObject("_id", partnerId);
//		String partnerName = db.getCollection("testFromVM").find(partnerQuery).first().getString("Name");

//		BasicDBObject productQuery = partnerQuery.append("Products", new BasicDBObject("_id", productId));
//		String productName = db.getCollection("testFromVM").find(productQuery).first().getString("Name");
		
		String partnerName = "Partner 1";
		String productName = "Product 1";

		StringBuilder builder = new StringBuilder("Partner: " + partnerId + " ");
		builder.append(partnerName + "\n");
		builder.append("Product: " + productId + " " + productName + "\n");
		builder.append("User: " + userId + "\n");
		return builder.toString();
	}

}
