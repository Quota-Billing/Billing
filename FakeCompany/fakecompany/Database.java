package fakecompany;

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

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.User;
public class Database {

	static Collection result = new ArrayList();
	private static Database instance;
	  
	  Document userDocument;
	
	 public static ArrayList getDatabaseInfo() {
		  MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds161016.mlab.com:61016/fakecompany"));
		  
	        try {
	            MongoDatabase database = mongoClient.getDatabase("fakecompany");
	 
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
	 
	 public String addUser(int userId, String name, String password, String token) {
		 
		    
		    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds161016.mlab.com:61016/fakecompany"));
		    
		    try {
		            MongoDatabase database = mongoClient.getDatabase("fakecompany");
		            MongoCollection<Document> collection = database.getCollection("user");
		            Document doc = new Document("_id", userId)
		                    .append("name", name)
		                    .append("password", password)
		                    .append("token", token);          
		           // .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
		            collection.insertOne(doc);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            mongoClient.close();
		        }
		    
		    return "ok";
		    
		  }

	 public static synchronized Database getInstance() {
		    if (instance == null) {
		      instance = new Database();
		    }
		    return instance;
		  }
}
