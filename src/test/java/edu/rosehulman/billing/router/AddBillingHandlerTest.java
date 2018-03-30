package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

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
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void testHandle() throws Exception {
		EasyMock.expect(request.params(":userID")).andReturn("testUserID");
		EasyMock.expect(request.params(":plan")).andReturn("testPlan");
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":fee")).andReturn("5.5");

		billingDatastore.addBilling("testUserID", "testPartnerID", "testProductID", "testPlan", 5.5);
		EasyMock.expectLastCall();
		EasyMock.expect(billingDatastore.getBilling("testUserID", "testPartnerID", "testProductID"))
				.andReturn(billingMock);
		billingHistoryDatastore.addBillingHistory("testUserID", "testPartnerID", "testProductID", billingMock);

		EasyMock.replay(billingDatastore, billingHistoryDatastore, billingMock, request, response);

		handler.handle(request, response);

		EasyMock.verify(billingDatastore, billingHistoryDatastore, billingMock, request, response);
	}

}
