package fakecompany;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
public class Database {

	private static Database instance;
	private MongoClient mongoClient;
	private Datastore datastore;
	
	private Database() {
		this.mongoClient = new MongoClient(
				new MongoClientURI("mongodb://team18:123456@ds161016.mlab.com:61016/fakecompany"));
		Morphia morphia = new Morphia();
		morphia.mapPackage("fakecompany");
		this.datastore = morphia.createDatastore(this.mongoClient, "fakecompany");

	}
	  
	
	 
	 public String addUser(String userId, String password, String token) {
		 		    
		    try {
		          User user = new User(userId);
		          user.setPassword(password);
		          user.setPaymentToken(token);
		          datastore.save(user);
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



	public User getUser(String userId) {
		List<User> users = datastore.createQuery(User.class).field("userId").equal(userId).asList();
		if (users.size() == 0) {
			System.out.println("wrong userId"); // debugging
			return null;
		}
		User user = users.get(0);
		return user;
	}
}
