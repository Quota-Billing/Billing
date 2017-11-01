package edu.rosehulman.billing;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.billing.models.User;
public class Database {
	private static Database instance;
	private static Collection<String> result = new ArrayList<String>();
	private static MongoClient mongoClient;
	private static MongoClient sharedMongoClient;
	private static MongoDatabase billingDB;
	private static MongoDatabase sharedDB;
	private Database() {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
        sharedMongoClient = new MongoClient(new MongoClientURI("mongodb://team18:123456@ds113785.mlab.com:13785/quotabillingshare"));
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        billingDB = mongoClient.getDatabase("billingpart").withCodecRegistry(pojoCodecRegistry);
        sharedDB = sharedMongoClient.getDatabase("quotabillingshare").withCodecRegistry(pojoCodecRegistry);

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
		
		try {

			MongoIterable<String> collections = sharedDB.listCollectionNames();
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

	

	// add a user
	public static String addUser(String partnerId, String productId, String userId) {
		System.out.println(productId);
		
		
		//User user = new User(userId, new ObjectId(productId), new ObjectId(partnerId));
		User user = new User();

		try {
			MongoCollection<User> collection = billingDB.getCollection("user", User.class);

			//Document doc = new Document("_id", userId);
			// .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			collection.insertOne(user);

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
			 * DBObject findQuery = new BasicDBObject("_id", 1); DBObject listItem = new
			 * BasicDBObject("user", 5); DBObject updateQuery = new BasicDBObject("$push",
			 * listItem);
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
		
		try {
			MongoCollection<Document> collection = billingDB.getCollection("Partner");

			collection.updateOne(new Document("_id", partnerId),
					new Document("$push", new Document("product", productId)));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";

	}

	public Quota getQuotaInfo(String partnerId, String productId, String userId, String quotaId) {
		// BasicDBObject query = new BasicDBObject("_id",
		// partnerId).append("Products", new BasicDBObject("_id", productId)
		// .append("Users", new BasicDBObject("_id", userId).append("Quotas",
		// new BasicDBObject("_id", quotaId))));

		// Fake data first, will change later
		//Quota quota = new Quota(0, "Data", "number");
		Quota quota = new Quota();
		List<Tier> tiers = new ArrayList<Tier>();
		tiers.add(new Tier("1", "free", 200, 0));
		tiers.add(new Tier("2", "premium", 1000, 20));
		quota.setTiers(tiers);

		return quota;
	}

	public String getPartnerBillingInfo(String partnerId, String productId, String userId) {
		// BasicDBObject partnerQuery = new BasicDBObject("_id", partnerId);
		// String partnerName =
		// db.getCollection("testFromVM").find(partnerQuery).first().getString("Name");

		// BasicDBObject productQuery = partnerQuery.append("Products", new
		// BasicDBObject("_id", productId));
		// String productName =
		// db.getCollection("testFromVM").find(productQuery).first().getString("Name");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		try {
			MongoDatabase database = mongoClient.getDatabase("billingpart");
			MongoCollection<Document> partnerCollection = database.getCollection("partner");
			Document partnerDoc = partnerCollection.find(eq("_id", partnerId)).first();
			
			Partner partner = new Partner(partnerId, (String) partnerDoc.get("name"), partnerDoc.getString("apiKey"));
//			String billingURL = partner.getBillingURL();
			
			MongoCollection<Document> productCollection = database.getCollection("product");
			
			MongoCollection<Document> userCollection = database.getCollection("user");
//			Document userDoc = userDocument.find(eq(""))
			
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		String partnerName = "Partner 1";
		String productName = "Product 1";

		StringBuilder builder = new StringBuilder("Partner: " + partnerId + " ");
		builder.append(partnerName + "\n");
		builder.append("Product: " + productId + " " + productName + "\n");
		builder.append("User: " + userId + "\n");
		return builder.toString();
	}

	public String addPartner(int partnerId, String password, String partnerName, int productId) {
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));

		try {
			MongoDatabase database = mongoClient.getDatabase("billingpart");
			MongoCollection<Document> collection = database.getCollection("partner");
			Document doc = new Document("_id", partnerId).append("password", password).append("partnerName", partnerName).append("productId", productId);

			// .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			collection.insertOne(doc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";
	}

	public String addProduct(int productId, String productName, int userId) {
		// TODO Auto-generated method stub
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));

		try {
			MongoDatabase database = mongoClient.getDatabase("billingpart");
			MongoCollection<Document> collection = database.getCollection("product");
			Document doc = new Document("_id", productId).append("productName", productName).append("userId", userId);

			// .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
			collection.insertOne(doc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			mongoClient.close();
		}

		return "ok";
	}
}
