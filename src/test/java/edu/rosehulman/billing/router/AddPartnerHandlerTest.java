package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Billing;
import spark.Request;
import spark.Response;

public class AddPartnerHandlerTest {

	AddPartnerHandler handler;
	PartnerDatastore partnerDatastore;
	Billing billingMock;
	Request request;
	Response response;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		billingMock = EasyMock.createMock(Billing.class);

		handler = new AddPartnerHandler(partnerDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() {

	}

}
