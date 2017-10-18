package edu.rosehulman.billing;

import java.util.ArrayList;
import java.util.Collection;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Database {
	private static Database instance;
	private static Collection<String> result = new ArrayList<String>();

	Document userDocument;

	private Database() {

	}

	// private Database() {

	// MongoClientURI uri = new
	// MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare");
	// MongoClient client = new MongoClient(uri);
	// MongoDatabase db = client.getDatabase(uri.getDatabase());
	//
	// //MongoCollection<Document> testdb = db.getCollection("");
	//
	// Set<String> colls = db.getCollectionNames();
	//
	// for (String s : colls) {
	// System.out.println(s);
	// }
	// // testdb.insertOne(userDocument);

	// }

	public static synchronized Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}
		return instance;
	}

	public static ArrayList<String> getSharedDatabaseInfo() {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));

		try {
			MongoDatabase database = mongoClient.getDatabase("quotabillingshare");

			MongoIterable<String> collections = database.listCollectionNames();
			for (String collectionName : collections) {
				System.out.println(collectionName);
				result.add(collectionName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return (ArrayList<String>) result;
	}

	// this will be deprecated soon, now just a test for connection
	public static String addTobillingdb(String partnerId, String productid, String userId) {

		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));

		try {
			MongoDatabase database = mongoClient.getDatabase("billingpart");
			MongoCollection<Document> collection = database.getCollection("user");
			Document doc = new Document("_id", userId);

			// .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			collection.insertOne(doc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";

	}

	// add a user
	public static String addUser(String partnerId, String productId, String userId) {

		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));

		try {
			MongoDatabase database = mongoClient.getDatabase("billingpart");
			MongoCollection<Document> collection = database.getCollection("user");
			Document doc = new Document("_id", userId);

			// .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			collection.insertOne(doc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";

		// partnerCollection.updateOne(and(eq("_id", partnerId), eq("products",
		// productId)), userDocument); // TODO This does not work, not using
		// lists
	}

	// add a user connecting to a product
	public String addUserToProduct(int i, int productId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));

		try {
			MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
			MongoCollection<Document> collection = database.getCollection("Product");

			/*
			 * DBObject findQuery = new BasicDBObject("_id", 1); DBObject
			 * listItem = new BasicDBObject("user", 5); DBObject updateQuery =
			 * new BasicDBObject("$push", listItem);
			 */
			collection.updateOne(new Document("_id", productId), new Document("$push", new Document("user", i)));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";
	}
	
	// add a product connecting to a partner
	public String addProductToPartner(int partnerId, int productId) {
	    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
	    
	    try {
	            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
	            MongoCollection<Document> collection = database.getCollection("Partner");
	            
	            collection.updateOne(new Document("_id", partnerId),
	                new Document("$push", new Document("product", productId)));
	            
	            
	    } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            mongoClient.close();
	        }
	    
	    return "ok";
	    
	  }
	  
}
