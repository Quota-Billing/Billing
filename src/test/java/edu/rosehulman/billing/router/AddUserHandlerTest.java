package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.UserDatastore;
import spark.Request;
import spark.Response;

public class AddUserHandlerTest {

	AddUserHandler handler;
	UserDatastore userDatastore;
	Request request;
	Response response;

	@Before
	public void setUp() throws Exception {
		userDatastore = EasyMock.createMock(UserDatastore.class);

		handler = new AddUserHandler(userDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerId");
		EasyMock.expect(request.params(":productId")).andReturn("testProductId");
		EasyMock.expect(request.params(":userId")).andReturn("testUserId");

		EasyMock.expect(userDatastore.addUser("testUserId", "testProductId", "testPartnerId")).andReturn("");

		EasyMock.replay(userDatastore, request, response);

		handler.handle(request, response);

		EasyMock.verify(userDatastore, request, response);
	}

}
