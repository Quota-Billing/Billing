package edu.rosehulman.billing.router;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import edu.rosehulman.billing.datastore.BillingHistoryDatastore;
import edu.rosehulman.billing.models.BillingHistory;
import spark.Request;
import spark.Response;

public class GetBillingHistoryHandlerTest {

	Request request;
	Response response;
	GetBillingHistoryHandler handler;
	BillingHistoryDatastore historyDatastore;
	BillingHistory history;

	@Before
	public void setUp() throws Exception {
		historyDatastore = EasyMock.createMock(BillingHistoryDatastore.class);

		handler = new GetBillingHistoryHandler(historyDatastore);

		request = EasyMock.createMock(Request.class);
		response = EasyMock.createMock(Response.class);
	}

	@Test
	public void test() throws Exception {
		EasyMock.expect(request.params(":id")).andReturn("testBillingID");
		EasyMock.expect(historyDatastore.getBillingHistory("testBillingID")).andReturn(history);

		EasyMock.replay(historyDatastore, request, response);

		handler.handle(request, response);

		EasyMock.verify(historyDatastore, request, response);
	}

}
