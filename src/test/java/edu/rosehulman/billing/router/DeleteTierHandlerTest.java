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

public class DeleteTierHandlerTest {

	Request request;
	Response response;
	DeleteTierHandler handler;
	TierDatastore tierDatastore;
	PartnerDatastore partnerDatastore;
	TierSharedService service;
	Quota quota;
	Partner partner;
	Product product;
	Tier tier;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		service = EasyMock.createMock(TierSharedService.class);
		tierDatastore = EasyMock.createMock(TierDatastore.class);

		handler = new DeleteTierHandler(tierDatastore, partnerDatastore, service);

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

		EasyMock.expect(partnerDatastore.getPartner("testPartnerID")).andReturn(partner);
		EasyMock.expect(partner.getProduct("testProductID")).andReturn(product);
		EasyMock.expect(product.getQuota("testQuotaID")).andReturn(quota);
		EasyMock.expect(service.UpdateTier("testPartnerID", "testProductID", "testQuotaID", "testTierID"))
				.andReturn(tier);
		tier.setPartner(partner);
		EasyMock.expectLastCall();
		tier.setProduct(product);
		EasyMock.expectLastCall();
		tier.setQuota(quota);
		EasyMock.expectLastCall();
		tierDatastore.deleteTier(tier);
		EasyMock.expectLastCall();

		EasyMock.replay(request, response, partner, product, quota, tier, tierDatastore, partnerDatastore, service);

		handler.handle(request, response);

		EasyMock.verify(request, response, partner, product, quota, tier, tierDatastore, partnerDatastore, service);
	}

}
