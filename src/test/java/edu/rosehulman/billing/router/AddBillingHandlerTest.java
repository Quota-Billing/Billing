package edu.rosehulman.billing.router;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.models.Billing;
import spark.Request;
import spark.Response;

public class AddBillingHandlerTest {
	
	AddBillingHandler handler;
	BillingDatastore billingDatastore;
	BillingHistoryDatastore billingHistoryDatastore;
	Billing billingMock;
	Request request;
	Response response;
	
	@Before
	public void setUp() throws Exception {
		billingDatastore = EasyMock.createMock(BillingDatastore.class);
		billingHistoryDatastore = EasyMock.createMock(BillingHistoryDatastore.class);
		billingMock = EasyMock.createMock(Billing.class);
		
		handler = new AddBillingHandler(billingDatastore, billingHistoryDatastore);
		
		request = EasyMock.createMock(Request.class);
	}
	
	@Test
	public void testHandle() throws UnirestException {
		billingDatastore.addBilling("testUserID", "testPartnerID", "testProductID", "testPlan", 5.5);
		EasyMock.expectLastCall();
		EasyMock.expect(billingDatastore.getBilling("testUserID", "testPartnerID", "testProductID")).andReturn(billingMock);
		billingHistoryDatastore.addBillingHistory("testUserID", "testPartnerID", "testProductID", billingMock);
		
		EasyMock.replay(billingDatastore, billingHistoryDatastore,billingMock);
		
		HttpRequestWithBody request = Unirest.post("http://localhost:8085/userId/testUserID/plan/testPlan/partnerId/testPartnerID/productId/testProductID/fee/5.5");
		int r = request.asString().getStatus();
		assertEquals(r, 200);
	
		EasyMock.verify(billingDatastore, billingHistoryDatastore,billingMock);
	}

}
