package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import spark.Request;
import spark.Response;

public class DeleteQuotaHandlerTest {

	Request request;
	Response response;
	DeleteQuotaHandler handler;
	QuotaDatastore quotaDatastore;
	Quota quota;
	Partner partner;
	Product product;

	// TODO: original class is not up to date to the new design

	@Before
	public void setUp() throws Exception {

		handler = new DeleteQuotaHandler();

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		product = EasyMock.createMock(Product.class);
		quota = EasyMock.createMock(Quota.class);
	}

	@Test
	public void test() {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":quotaId")).andReturn("testQuotaID");

	}

}
