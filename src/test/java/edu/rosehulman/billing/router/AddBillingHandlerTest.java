package edu.rosehulman.billing.router;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Test;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;

import edu.rosehulman.billing.datastore.BillingDatastore;
import edu.rosehulman.billing.datastore.BillingHistoryDatastore;

public class AddBillingHandlerTest {

	@Test
	public void testHandle() {
		BillingDatastore billingDatastoreMock = EasyMock.createMock(BillingDatastore.class);
		BillingHistoryDatastore historyDatastoreMock = EasyMock.createMock(BillingHistoryDatastore.class);
		
		EasyMock.replay(billingDatastoreMock, historyDatastoreMock);
		HttpRequestWithBody request = Unirest.delete(String.format("http://localhost:%d/web/e2e/delete/hello.txt", port));
		int r = request.asString().getStatus();
		assertEquals(r, 200);
//		assertEquals(file.exists(), false);
	
	}

}


