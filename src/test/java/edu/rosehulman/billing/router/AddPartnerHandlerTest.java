package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.sharedservice.PartnerSharedService;
import spark.Request;
import spark.Response;

public class AddPartnerHandlerTest {

	AddPartnerHandler handler;
	PartnerDatastore partnerDatastore;
	PartnerSharedService service;
	Partner partner;
	Billing billingMock;
	Request request;
	Response response;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		billingMock = EasyMock.createMock(Billing.class);
		service = EasyMock.createMock(PartnerSharedService.class);

		handler = new AddPartnerHandler(partnerDatastore, service);

		partner = EasyMock.createMock(Partner.class);
		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerId");
		EasyMock.expect(service.UpdatePartner("testPartnerId")).andReturn(partner);

		EasyMock.expect(partnerDatastore.addPartnerDirect(partner)).andReturn("");

		EasyMock.replay(partnerDatastore, billingMock, service, partner, request, response);

		handler.handle(request, response);

		EasyMock.verify(partnerDatastore, billingMock, service, partner, request, response);
	}

}
