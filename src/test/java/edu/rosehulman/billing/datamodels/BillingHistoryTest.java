package edu.rosehulman.billing.datamodels;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.rosehulman.billing.models.BillingHistory;

public class BillingHistoryTest {
	
	@Test
	public void TestBillingHistory() {
		BillingHistory bh = new BillingHistory(1);

		assertEquals(1, bh.getBillingHistoryId());
	}
	
	public void TestSetBillingHistoryId() {
		BillingHistory bh = new BillingHistory(1);
		bh.setBillingHistoryId(2);
		assertEquals(2, bh.getBillingHistoryId());
	}
	
	public void Test
}
