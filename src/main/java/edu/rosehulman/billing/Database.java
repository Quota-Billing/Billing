package edu.rosehulman.billing;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import edu.rosehulman.billing.Database;

public class Database {
	private static Database instance;
	static Collection result = new ArrayList();
	
	  
	  Document userDocument;
	  
	//  private Database() {
		  
	        
//		  MongoClientURI uri  = new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"); 
//		  MongoClient client = new MongoClient(uri);
//		  MongoDatabase db = client.getDatabase(uri.getDatabase());
//		    
//		  //MongoCollection<Document> testdb = db.getCollection("");
//		  
//		  Set<String> colls = db.getCollectionNames();
//
//		  for (String s : colls) {
//		  System.out.println(s);
//		  }
//		 // testdb.insertOne(userDocument); 
		  
		  
		  
		 
		  
	//  }
	  
	  public static synchronized Database getInstance() {
		    if (instance == null) {
		      instance = new Database();
		    }
		    return instance;
		  }
	  
	  public static ArrayList getSharedDatabaseInfo() {
		  MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
		  
	        try {
	            MongoDatabase database = mongoClient.getDatabase("quotabillingshare");
	 
	            MongoIterable <String> collections = database.listCollectionNames();
	            for (String collectionName: collections) {
	                System.out.println(collectionName);
	                result.add(collectionName);
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            mongoClient.close();
	        }
	        
	       
			return (ArrayList) result;
	  }
	  
	  //add quota to our own database
	  public static String addTobillingdb(String quotaId, String quotaName, String quotaType) {
		  String id = quotaId;
		  String name = quotaName;
		  String type = quotaType;
		  
		  MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		  
		  try {
	            MongoDatabase database = mongoClient.getDatabase("billingpart");
	            MongoCollection<Document> collection = database.getCollection("quota");
	            Document doc = new Document("_id", id)
	                    .append("name", name)
	                    .append("type", type);
	            
	           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
	            collection.insertOne(doc);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            mongoClient.close();
	        }
		  
		  return "ok";
		  
	  }

	  public void addUser(String partnerId, String productId, String userId) {
		 
	    userDocument = new Document("$set", new Document("_id", partnerId)
	        .append("products", new Document("_id", productId)
	            .append("users", new Document("_id", userId))));
	    
	   
	   // partnerCollection.updateOne(and(eq("_id", partnerId), eq("products", productId)), userDocument); // TODO This does not work, not using lists
	  }
}
