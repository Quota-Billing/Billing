package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.UserDatastore;
import spark.Request;
import spark.Response;

public class DeleteUserHandlerTest {

	Request request;
	Response response;
	DeleteUserHandler handler;
	UserDatastore userDatastore;

	@Before
	public void setUp() throws Exception {
		userDatastore = EasyMock.createMock(UserDatastore.class);

		handler = new DeleteUserHandler(userDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":partnerId")).andReturn("testPartnerID");
		EasyMock.expect(request.params(":productId")).andReturn("testProductID");
		EasyMock.expect(request.params(":userId")).andReturn("testUserID");

		EasyMock.expect(userDatastore.deleteUser("testPartnerID", "testProductID", "testUserID")).andReturn(true);

		EasyMock.replay(userDatastore, request, response);

		handler.handle(request, response);

		EasyMock.verify(userDatastore, request, response);
	}

}
