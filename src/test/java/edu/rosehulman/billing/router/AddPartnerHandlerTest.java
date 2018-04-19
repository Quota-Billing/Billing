package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.sharedservice.PartnerSharedService;
import spark.Request;
import spark.Response;

public class AddPartnerHandlerTest {

	AddPartnerHandler handler;
	PartnerDatastore partnerDatastore;
	PartnerSharedService service;

	Billing billingMock;
	Request request;
	Response response;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		billingMock = EasyMock.createMock(Billing.class);
		service = EasyMock.createMock(PartnerSharedService.class);

		handler = new AddPartnerHandler(partnerDatastore, service);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() {

	}

}
