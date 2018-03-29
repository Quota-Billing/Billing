package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.User;

public class BillingTest {

	@Test
	public void testSetAndGetUser() {
		User userMock = EasyMock.createMock(User.class);
		Billing billing = new Billing();
		billing.setUser(userMock);
		assertEquals(billing.getUser(), userMock);
	}

	@Test
	public void testSetAndGetUserId() {
		User userMock = EasyMock.createMock(User.class);
		Billing billing = new Billing();
		billing.setUser(userMock);

		EasyMock.expect(userMock.getId()).andReturn("userID");
		EasyMock.replay(userMock);

		assertEquals("userID", billing.getUserId());

		EasyMock.verify(userMock);
	}

	@Test
	public void testSetAndGetFeeZero() {
		Billing billing = new Billing();
		billing.setFee(0.0);

		assertEquals(0.0, billing.getFee(), 0.00001);
	}

	@Test
	public void testSetAndGetFeePositive() {
		Billing billing = new Billing();
		billing.setFee(5.5);

		assertEquals(5.5, billing.getFee(), 0.00001);
	}

	@Test
	public void testSetAndGetFeeNegative() {
		Billing billing = new Billing();
		billing.setFee(-5.5);

		assertEquals(-5.5, billing.getFee(), 0.00001);
	}

	@Test
	public void testSetAndGetPlan() {
		Billing billing = new Billing();
		billing.setPlan("APlan");

		assertEquals("APlan", billing.getPlan());
	}

	@Test
	public void testSetAndGetPaid() {
		Billing billing = new Billing();

		assertFalse(billing.getPaid());

		billing.setPaid();
		assertTrue(billing.getPaid());
	}

	@Test
	public void testConstructor() {
		User userMock = EasyMock.createMock(User.class);
		EasyMock.expect(userMock.getId()).andReturn("userID");
		EasyMock.replay(userMock);

		Billing b = new Billing(userMock, "aPlan", 5.0);

		assertFalse(b.getPaid());
		assertEquals("aPlan", b.getPlan());
		assertEquals(5.0, b.getFee(), 0.00001);
		assertEquals(userMock, b.getUser());
		assertEquals("userID", b.getUserId());

		EasyMock.verify(userMock);
	}
}
