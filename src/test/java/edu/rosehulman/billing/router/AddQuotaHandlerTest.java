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

public class AddQuotaHandlerTest {

	QuotaDatastore quotaDatastore;
	Request request;
	Response response;
	AddQuotaHandler handler;
	Quota quota;
	Partner partner;
	Product product;

	@Before
	public void setUp() throws Exception {
		quotaDatastore = EasyMock.createMock(QuotaDatastore.class);
		// billingMock = EasyMock.createMock(Billing.class);
		//
		handler = new AddQuotaHandler(quotaDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		quota = EasyMock.createMock(Quota.class);
		partner = EasyMock.createMock(Partner.class);
		product = EasyMock.createMock(Product.class);
	}

	@Test
	public void test() {

	}

}
