package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;

public class DeleteBillingHandlerTest {

	Request request;
	Response response;
	DeleteBillingHandler handler;
	BillingDatastore billingDatastore;
	Partner partner;
	Product product;
	Quota quota;
	Tier tier;

	@Before
	public void setUp() throws Exception {
		billingDatastore = EasyMock.createMock(BillingDatastore.class);

		handler = new DeleteBillingHandler(billingDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":billingId")).andReturn("testBillingID");
		EasyMock.expect(request.params(":userID")).andReturn("testUserID");
		EasyMock.expect(request.params(":plan")).andReturn("testPlan");
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":fee")).andReturn("5.5");

		billingDatastore.deleteBilling("testUserID", "testPartnerID", "testProductID");
		EasyMock.expectLastCall();

		EasyMock.replay(billingDatastore, request, response);

		handler.handle(request, response);

		EasyMock.verify(billingDatastore, request, response);
	}

}
