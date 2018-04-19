package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;
import edu.rosehulman.sharedservice.TierSharedService;
import spark.Request;
import spark.Response;

public class AddTierHandlerTest {

	Request request;
	Response response;
	AddTierHandler handler;
	TierDatastore tierDatastore;
	TierSharedService service;
	PartnerDatastore partnerDatastore;
	Partner partner;
	Product product;
	Quota quota;
	Tier tier;

	@Before
	public void setUp() throws Exception {
		tierDatastore = EasyMock.createMock(TierDatastore.class);
		service = EasyMock.createMock(TierSharedService.class);
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);

		handler = new AddTierHandler(tierDatastore, service, partnerDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		quota = EasyMock.createMock(Quota.class);
		tier = EasyMock.createMock(Tier.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerId");
		EasyMock.expect(request.params(":productId")).andReturn("testProductId");
		EasyMock.expect(request.params(":tierId")).andReturn("testTierId");
		EasyMock.expect(request.params(":quotaId")).andReturn("testQuotaId");

		// TODO: get partner mock
		EasyMock.expect(partner.getProduct("testProductId")).andReturn(product);
		EasyMock.expect(product.getQuota("testQuotaId")).andReturn(quota);

		tierDatastore.addTier(tier);
		EasyMock.expectLastCall();

		EasyMock.replay(tier, tierDatastore, request, response, partner, quota);
		// TODO: not done

		handler.handle(request, response);

		EasyMock.verify(tier, tierDatastore, request, response, partner, quota);
	}

}
