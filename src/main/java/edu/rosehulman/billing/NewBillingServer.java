package edu.rosehulman.billing;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.datastore.UserDatastore;
import edu.rosehulman.billing.router.AddBillingHandler;
import edu.rosehulman.billing.router.AddPartnerHandler;
import edu.rosehulman.billing.router.AddProductHandler;
import edu.rosehulman.billing.router.AddQuotaHandler;
import edu.rosehulman.billing.router.AddTierHandler;
import edu.rosehulman.billing.router.AddTierToUserHandler;
import edu.rosehulman.billing.router.AddUserHandler;
import edu.rosehulman.billing.router.DeleteBillingHandler;
import edu.rosehulman.billing.router.DeletePartnerHandler;
import edu.rosehulman.billing.router.DeleteProductHandler;
import edu.rosehulman.billing.router.DeleteQuotaHandler;
import edu.rosehulman.billing.router.DeleteTierHandler;
import edu.rosehulman.billing.router.DeleteUserHandler;
import edu.rosehulman.billing.router.GetBillingHistoryHandler;
import edu.rosehulman.billing.router.QuotaReachedHandler;
import edu.rosehulman.billing.router.Routes;
import edu.rosehulman.billing.router.UpdatePartnerHandler;

public class NewBillingServer {
	public static void main(String[] args) {
		port(8085); // Set the port to run on

		Unirest.setObjectMapper(new ObjectMapper() {
			private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new ObjectIdMapper();

			public <T> T readValue(String value, Class<T> valueType) {
				try {
					return jacksonObjectMapper.readValue(value, valueType);
				} catch (IOException e) {
					System.out.println("read error: " + value);
					System.out.println("value typte: " + valueType.toString());
					throw new RuntimeException(e);
				}
			}

			public String writeValue(Object value) {
				try {
					return jacksonObjectMapper.writeValueAsString(value);
				} catch (JsonProcessingException e) {
					System.out.println("error: " + value);

					throw new RuntimeException(e);
				}
			}
		});
		
		MongoClient mongoClient = new MongoClient(
				new MongoClientURI("mongodb://admin:admin@ds117495.mlab.com:17495/billingpart"));
		Morphia morphia = new Morphia();
		morphia.mapPackage("edu.rosehulman.billingpart");
		Datastore datastore = morphia.createDatastore(mongoClient, "billingpart");
		
		
		BillingDatastore billingstore = new BillingDatastore(datastore);
		BillingHistoryDatastore billinghistorystore = new BillingHistoryDatastore(datastore);
		PartnerDatastore partnerstore = new PartnerDatastore(datastore);
		ProductDatastore productstore = new ProductDatastore(datastore);
		QuotaDatastore quotastore = new QuotaDatastore(datastore);
		TierDatastore tierstore = new TierDatastore(datastore);
		UserDatastore userstore = new UserDatastore(datastore);
		
		post("/addUser/partner/:partnerId/product/:productId/user/:userId", (req, res) -> {
			// after getting post call, call sharedclient to pull updates
			return SharedClient.getInstance().addUserInfo(req.params(":partnerId"), req.params(":productId"),
					req.params(":userId"));
		});
		post(Routes.QUOTA_REACHED, new QuotaReachedHandler(billingstore, billinghistorystore, tierstore));

		post(Routes.ADD_BILLING, new AddBillingHandler(billingstore, billinghistorystore));
		post(Routes.ADD_USER, new AddUserHandler(userstore));
		post(Routes.ADD_PARTNER, new AddPartnerHandler(partnerstore));
		post(Routes.ADD_PRODUCT_TO_PARTNER, new AddProductHandler(partnerstore, productstore));
		post(Routes.ADD_QUOTA, new AddQuotaHandler(quotastore));
		post(Routes.ADD_Tier, new AddTierHandler(tierstore));
    //Fakecompany
		post(Routes.ADD_Tier_TO_USER, new AddTierToUserHandler(userstore, tierstore));
		post(Routes.BILLING_PAID, new BillingPaidHandler());
		//master
		get(Routes.BILLINGHISTORY, new GetBillingHistoryHandler());

		
		delete(Routes.DELETE_BILLING, new DeleteBillingHandler());
		delete(Routes.DELETE_USER, new DeleteUserHandler());
		delete(Routes.DELETE_PARTNER, new DeletePartnerHandler());
		delete(Routes.DELETE_PRODUCT_TO_PARTNER, new DeleteProductHandler());
		delete(Routes.DELETE_QUOTA, new DeleteQuotaHandler());
		delete(Routes.DELETE_Tier, new DeleteTierHandler());
    //Fakecompany
    put(Routes.UPDATE_PARTNER, new UpdatePartnerHandler());

		// BrainTree br =new BrainTree();

		JobScheduler scheduler = new JobScheduler();
	}
}
