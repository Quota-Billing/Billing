package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.User;

public class BillingTest {

	@Test
	public void TestBilling() {
		User user = new User("123");
		Billing billing = new Billing(user, "1", 1.0);
		// test billing
		assertEquals("123", billing.getUserId());

		assertEquals(1.0, billing.getFee(), 0.001);

		assertEquals("1", billing.getPlan());
	}

	@Test
	public void TestBillingSet() {
		User user = new User("123");
		Billing billing = new Billing(user, "1", 1.0);
		// test billing
		assertEquals("123", billing.getUserId());

		assertEquals(1.0, billing.getFee(), 0.001);

		assertEquals("1", billing.getPlan());
		billing.setFee(2.9);
		assertEquals(2.9, billing.getFee(), 0.001);
	}
}
