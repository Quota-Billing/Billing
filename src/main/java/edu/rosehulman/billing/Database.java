package edu.rosehulman.billing;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.rosehulman.billing.Database;

public class Database {
	private static Database instance;

	  private MongoCollection<Document> partnerCollection;

	  private Database() {
	    MongoClient mongoClient = new MongoClient("localhost", 27017); // TODO set these in config
	    MongoDatabase database = mongoClient.getDatabase("quotabilling");
	    partnerCollection = database.getCollection("partner");
	  }

	  public static synchronized Database getInstance() {
	    if (instance == null) {
	      instance = new Database();
	    }
	    return instance;
	  }

	  public void addUser(String partnerId, String productId, String userId) {
	    Document userDocument = new Document("$set", new Document("_id", partnerId)
	        .append("products", new Document("_id", productId)
	            .append("users", new Document("_id", userId))));
	    partnerCollection.updateOne(and(eq("_id", partnerId), eq("products", productId)), userDocument); // TODO This does not work, not using lists
	  }
}
