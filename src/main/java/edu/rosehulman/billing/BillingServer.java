package edu.rosehulman.billing;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import edu.rosehulman.billing.router.AddBillingHandler;
import edu.rosehulman.billing.router.AddPartnerHandler;
import edu.rosehulman.billing.router.AddProductHandler;
import edu.rosehulman.billing.router.AddQuotaHandler;
import edu.rosehulman.billing.router.AddTierHandler;
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

public class BillingServer {
	public static ArrayList<String> dbinfo;

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
		post("/addUser/partner/:partnerId/product/:productId/user/:userId", (req, res) -> {
			// after getting post call, call sharedclient to pull updates
			return SharedClient.getInstance().addUserInfo(req.params(":partnerId"), req.params(":productId"),
					req.params(":userId"));
		});
		post(Routes.QUOTA_REACHED, new QuotaReachedHandler());

		post(Routes.ADD_BILLING, new AddBillingHandler());
		post(Routes.ADD_USER, new AddUserHandler());
		delete(Routes.DELETE_USER, new DeleteUserHandler());
		post(Routes.ADD_PARTNER, new AddPartnerHandler());
		post(Routes.ADD_PRODUCT_TO_PARTNER, new AddProductHandler());
		post(Routes.ADD_QUOTA, new AddQuotaHandler());
		post(Routes.ADD_Tier, new AddTierHandler());
		
		get(Routes.BILLINGHISTORY, new GetBillingHistoryHandler());
		
		delete(Routes.DELETE_BILLING, new DeleteBillingHandler());
		delete(Routes.DELETE_USER, new DeleteUserHandler());
		delete(Routes.DELETE_PARTNER, new DeletePartnerHandler());
		delete(Routes.DELETE_PRODUCT_TO_PARTNER, new DeleteProductHandler());
		delete(Routes.DELETE_QUOTA, new DeleteQuotaHandler());
		delete(Routes.DELETE_Tier, new DeleteTierHandler());

		// BrainTree br =new BrainTree();

		JobScheduler scheduler = new JobScheduler();
	}
}
