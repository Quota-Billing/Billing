package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.rosehulman.billing.BillingClient;
import edu.rosehulman.billing.models.Billing;

public class BillingTest {

	@Test
	public void TestBilling() {
		Billing billing = new Billing(1, 1, "");
		//test billing
		assertEquals(1, billing.getBillingID());
		
		assertEquals(1, billing.getUserId());
		
		assertEquals("", billing.getPlan());
	}
}
