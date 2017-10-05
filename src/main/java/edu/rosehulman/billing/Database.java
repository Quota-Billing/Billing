package edu.rosehulman.billing;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.rosehulman.billing.Database;

public class Database {
	private static Database instance;
	  
	  Document userDocument;
	  
	  private Database() {
		  
		  MongoClientURI uri  = new MongoClientURI("mongodb://admin:admin@ds159662.mlab.com:59662/srproj-test1"); 
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
		 
	    userDocument = new Document("$set", new Document("_id", partnerId)
	        .append("products", new Document("_id", productId)
	            .append("users", new Document("_id", userId))));
	    
	   
	   // partnerCollection.updateOne(and(eq("_id", partnerId), eq("products", productId)), userDocument); // TODO This does not work, not using lists
	  }
}
