package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.models.Partner;
import spark.Request;
import spark.Response;

public class DeletePartnerHandlerTest {

	Request request;
	Response response;
	DeletePartnerHandler handler;
	PartnerDatastore partnerDatastore;
	Partner partner;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);

		handler = new DeletePartnerHandler(partnerDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
	}

	@Test
	public void test() throws Exception {
		// TODO: NOT DONE
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");

		EasyMock.expect(partnerDatastore.deletePartnerDirect(partner)).andReturn("");

		EasyMock.replay(request, response, partnerDatastore, partner);

		handler.handle(request, response);

		EasyMock.verify(request, response, partnerDatastore, partner);
	}

}
