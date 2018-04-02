package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;

public class DeleteTierHandlerTest {

	Request request;
	Response response;
	DeleteTierHandler handler;
	QuotaDatastore quotaDatastore;
	TierDatastore tierDatastore;
	Quota quota;
	Partner partner;
	Product product;
	Tier tier;

	// TODO: class is not up to date
	@Before
	public void setUp() throws Exception {

		tierDatastore = EasyMock.createMock(TierDatastore.class);

		handler = new DeleteTierHandler(tierDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		product = EasyMock.createMock(Product.class);
		quota = EasyMock.createMock(Quota.class);
		tier = EasyMock.createMock(Tier.class);
	}

	@Test
	public void test() throws Exception {

		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":tierId")).andReturn("testTierID");
		EasyMock.expect(request.params(":quotaId")).andReturn("testQuotaID");

		tierDatastore.deleteTier(tier);
		EasyMock.expectLastCall();

		EasyMock.replay(request, response, partner, product, quota, tier, tierDatastore);

		handler.handle(request, response);

		EasyMock.replay(request, response, partner, product, quota, tier, tierDatastore);
	}

}
