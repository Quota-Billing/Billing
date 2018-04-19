package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.PartnerDatastore;
import edu.rosehulman.billing.datastore.ProductDatastore;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.sharedservice.ProductSharedService;
import spark.Request;
import spark.Response;

public class AddProductHandlerTest {

	Request request;
	Response response;
	PartnerDatastore partnerDatastore;
	ProductDatastore productDatastore;
	AddProductHandler handler;
	ProductSharedService productSharedService;
	Partner partner;
	Product product;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		productDatastore = EasyMock.createMock(ProductDatastore.class);
		productSharedService = EasyMock.createMock(ProductSharedService.class);
		// billingMock = EasyMock.createMock(Billing.class);
		//
		handler = new AddProductHandler(partnerDatastore, productDatastore, productSharedService);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() {

	}

}
