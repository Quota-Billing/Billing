package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;

public class AddTierHandlerTest {

	Request request;
	Response response;
	AddTierHandler handler;
	TierDatastore tierDatastore;
	Partner partner;
	Product product;
	Quota quota;
	Tier tier;

	@Before
	public void setUp() throws Exception {
		tierDatastore = EasyMock.createMock(TierDatastore.class);

		handler = new AddTierHandler(tierDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		quota = EasyMock.createMock(Quota.class);
		tier = EasyMock.createMock(Tier.class);
	}

	@Test
	public void test() {

	}

}
