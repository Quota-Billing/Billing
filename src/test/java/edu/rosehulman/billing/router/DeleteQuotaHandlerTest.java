package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.QuotaDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.sharedservice.QuotaSharedService;
import spark.Request;
import spark.Response;

public class DeleteQuotaHandlerTest {

	Request request;
	Response response;
	DeleteQuotaHandler handler;
	QuotaDatastore quotaDatastore;
	PartnerDatastore partnerDatastore;
	QuotaSharedService service;
	Quota quota;
	Partner partner;
	Product product;

	@Before
	public void setUp() throws Exception {
		quotaDatastore = EasyMock.createMock(QuotaDatastore.class);
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		service = EasyMock.createMock(QuotaSharedService.class);

		handler = new DeleteQuotaHandler(quotaDatastore, partnerDatastore, service);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		product = EasyMock.createMock(Product.class);
		quota = EasyMock.createMock(Quota.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":quotaId")).andReturn("testQuotaID");
		EasyMock.expect(service.UpdateQuota("testProductID", "testPartnerID", "testQuotaID")).andReturn(quota);
		EasyMock.expect(partnerDatastore.getPartner("testPartnerID")).andReturn(partner);
		EasyMock.expect(partner.getProduct("testProductID")).andReturn(product);
		quota.setPartner(partner);
		EasyMock.expectLastCall();
		quota.setProduct(product);
		EasyMock.expectLastCall();
		quotaDatastore.deleteQuota(quota);
		EasyMock.expectLastCall();

		EasyMock.replay(quotaDatastore, partnerDatastore, service, request, response, partner, product, quota);

		handler.handle(request, response);

		EasyMock.verify(quotaDatastore, partnerDatastore, service, request, response, partner, product, quota);
	}

}
