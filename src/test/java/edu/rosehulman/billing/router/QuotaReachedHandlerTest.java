package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.datastore.TierDatastore;
import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Tier;
import spark.Request;
import spark.Response;

public class QuotaReachedHandlerTest {

	Request request;
	Response response;
	QuotaReachedHandler handler;
	BillingDatastore billingDatastore;
	BillingHistoryDatastore billingHistoryDatastore;
	TierDatastore tierDatastore;
	Tier tier;
	Billing billing;
	Partner partner;

	@Before
	public void setUp() throws Exception {
		billingDatastore = EasyMock.createMock(BillingDatastore.class);
		billingHistoryDatastore = EasyMock.createMock(BillingHistoryDatastore.class);
		tierDatastore = EasyMock.createMock(TierDatastore.class);

		handler = new QuotaReachedHandler(billingDatastore, billingHistoryDatastore, tierDatastore);

		tier = EasyMock.createMock(Tier.class);
		billing = EasyMock.createMock(Billing.class);
		partner = EasyMock.createMock(Partner.class);
		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":userId")).andReturn("tesUserID");
		EasyMock.expect(request.params(":quotaId")).andReturn("testQuotaID");
		EasyMock.expect(request.params(":tierId")).andReturn("testTierID");

		EasyMock.expect(
				tierDatastore.getTier("testPartnerID", "testProductID", "tesUserID", "testQuotaID", "testTierID"))
				.andReturn(tier);
		EasyMock.expect(tier.getMax()).andReturn(10);
		EasyMock.expect(tier.getName()).andReturn("testTierName");
		EasyMock.expect(tier.getPrice()).andReturn(1.0);

		billingDatastore.addBilling("tesUserID", "testPartnerID", "testProductID", "credit card", 1.0);
		EasyMock.expectLastCall();

		EasyMock.expect(billingDatastore.getBilling("tesUserID", "testPartnerID", "testProductID")).andReturn(billing);
		billingHistoryDatastore.addBillingHistory("tesUserID", "testPartnerID", "testProductID", billing);
		EasyMock.expectLastCall();

		// TODO: NOT DONE
	}

}
