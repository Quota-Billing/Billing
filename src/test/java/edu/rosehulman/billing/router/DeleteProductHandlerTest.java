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

public class DeleteProductHandlerTest {

	Request request;
	Response response;
	DeleteProductHandler handler;
	PartnerDatastore partnerDatastore;
	ProductDatastore productDatastore;
	ProductSharedService service;
	Partner partner;
	Product product;

	@Before
	public void setUp() throws Exception {
		partnerDatastore = EasyMock.createMock(PartnerDatastore.class);
		productDatastore = EasyMock.createMock(ProductDatastore.class);
		service = EasyMock.createMock(ProductSharedService.class);

		handler = new DeleteProductHandler(partnerDatastore, productDatastore, service);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
		partner = EasyMock.createMock(Partner.class);
		product = EasyMock.createMock(Product.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(partnerDatastore.getPartner("testPartnerID")).andReturn(partner);
		EasyMock.expect(service.UpdateProduct("testProductID", "testPartnerID")).andReturn(product);
		EasyMock.expect(productDatastore.deleteProductDirect(product, partner)).andReturn("ok");

		EasyMock.replay(partner, partnerDatastore, product, productDatastore, request, response, service);

		handler.handle(request, response);

		EasyMock.verify(partner, partnerDatastore, product, productDatastore, request, response, service);
	}

}
