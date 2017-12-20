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
public class Database {

	static Collection result = new ArrayList();
	
	  
	  Document userDocument;
	
	 public static ArrayList getSharedDatabaseInfo() {
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
}
