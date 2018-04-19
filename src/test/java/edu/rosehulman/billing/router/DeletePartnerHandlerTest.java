package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.sharedservice.PartnerSharedService;
import spark.Request;
import spark.Response;

public class DeletePartnerHandlerTest {

	Request request;
	Response response;
	DeletePartnerHandler handler;
	PartnerDatastore partnerDatastore;
	PartnerSharedService service;
	Partner partner;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		service = EasyMock.createMock(PartnerSharedService.class);

		handler = new DeletePartnerHandler(partnerDatastore, service);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(service.UpdatePartner("testPartnerID")).andReturn(partner);
		EasyMock.expect(partnerDatastore.deletePartnerDirect(partner)).andReturn("");

		EasyMock.replay(request, response, partnerDatastore, partner, service);

		handler.handle(request, response);

		EasyMock.verify(request, response, partnerDatastore, partner, service);
	}

}
