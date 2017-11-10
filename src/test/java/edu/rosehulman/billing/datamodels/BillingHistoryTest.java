package edu.rosehulman.billing.datamodels;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.BillingHistory;
import edu.rosehulman.billing.models.User;

public class BillingHistoryTest {
	
	@Test
	public void TestBillingHistory() {
		BillingHistory bh = new BillingHistory();

		assertEquals(0, bh.getBillingList().size());
	}
	
	@Test
	public void TestSetTimeStamp() {
		User user = new User("1");
		BillingHistory bh = new BillingHistory("1 year", user);
		bh.setTimeStamp("One month");
		assertEquals("One month", bh.getTimeStamp());
	}
	
	@Test
	public void TestAddBilling() {
		User user = new User("1");
		BillingHistory bh = new BillingHistory("1 year", user);
		Billing billing = new Billing(user, "1", 1.0);
		bh.addBilling(billing);
		assertEquals(billing, bh.getBillingList().get(0));
	}
	
	@Test
	public void TestDeleteBilling() {
		User user = new User("1");
		BillingHistory bh = new BillingHistory("1 year", user);
		Billing billing = new Billing(user, "1", 1.0);
		bh.addBilling(billing);
		bh.deleteBilling(billing);
		ArrayList<Billing> ans = new ArrayList<Billing>();
		assertEquals(bh.getBillingList(), ans);
	}
	
	@Test
	public void TestFindBilling() {
		User user = new User("1");
		BillingHistory bh = new BillingHistory("1 year", user);
		Billing billing = new Billing(user, "1", 1.0);
		bh.addBilling(billing);
		assertEquals(billing, bh.findBilling(billing));
		User user2 = new User("12");
		Billing billing2 = new Billing(user2, "1", 1.0);
		assertEquals(null, bh.findBilling(billing2));
	}
//	
//	@Test
//	public void TestUpdateBilling() {
//		BillingHistory bh = new BillingHistory(1);
//		Billing billing = new Billing(1, 1, "planA");
//		bh.addBilling(billing);
//		assertEquals(billing, bh.getBillingList().get(0));
//		assertEquals("Billing History: 1\nBillingId: 1\nUserId: 1\nPlan: planA\n", bh.toString());
//		billing.setPlan("planB");
//		assertEquals("Billing History: 1\nBillingId: 1\nUserId: 1\nPlan: planB\n", bh.toString());
//		billing.setUserID(2);
//		assertEquals("Billing History: 1\nBillingId: 1\nUserId: 2\nPlan: planB\n", bh.toString());
//	}
//	
//	@Test
//	public void TestGetBillingId() {
//		BillingHistory bh = new BillingHistory(1);
//		Billing billing = new Billing(1, 1, "planA");
//		bh.addBilling(billing);
//		assertEquals("[1]", bh.getBillingIdList().toString());
//	}
}
