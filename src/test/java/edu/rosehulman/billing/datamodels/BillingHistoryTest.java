package edu.rosehulman.billing.datamodels;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.rosehulman.billing.models.Billing;
import edu.rosehulman.billing.models.BillingHistory;

public class BillingHistoryTest {
	
	@Test
	public void TestBillingHistory() {
		BillingHistory bh = new BillingHistory(1);

		assertEquals(1, bh.getBillingHistoryId());
	}
	
	@Test
	public void TestSetBillingHistoryId() {
		BillingHistory bh = new BillingHistory(1);
		bh.setBillingHistoryId(2);
		assertEquals(2, bh.getBillingHistoryId());
	}
	
	@Test
	public void TestSetTimeStamp() {
		BillingHistory bh = new BillingHistory(1);
		bh.setTimeStamp("One month");
		assertEquals("One month", bh.getTimeStamp());
	}
	
	@Test
	public void TestSetFee() {
		BillingHistory bh = new BillingHistory(1);
		bh.setFee(1.23);
		assertEquals(1.23, bh.getFee(), 0.0001);
	}
	
	@Test
	public void TestAddBilling() {
		BillingHistory bh = new BillingHistory(1);
		Billing billing = new Billing(1, 1, "planA");
		bh.addBilling(billing);
		assertEquals(billing, bh.getBillingList().get(0));
	}
	
	@Test
	public void TestDeleteBilling() {
		BillingHistory bh = new BillingHistory(1);
		Billing billing = new Billing(1, 1, "planA");
		bh.addBilling(billing);
		bh.deleteBilling(billing);
		ArrayList<Billing> ans = new ArrayList<Billing>();
		assertEquals(bh.getBillingList(), ans);
		bh.addBilling(billing);
		bh.deleteBilling(billing.getBillingID());
		assertEquals(bh.getBillingList(), ans);
	}
	
	@Test
	public void TestFindBilling() {
		BillingHistory bh = new BillingHistory(1);
		Billing billing = new Billing(1, 1, "planA");
		bh.addBilling(billing);
		assertEquals(true, bh.findBilling(billing));
		assertEquals(true, bh.findBilling(billing.getBillingID()));
		Billing billing2 = new Billing(2, 1, "planA");
		assertEquals(false, bh.findBilling(billing2));
		assertEquals(false, bh.findBilling(billing2.getBillingID()));
	}
	
	@Test
	public void TestUpdateBilling() {
		BillingHistory bh = new BillingHistory(1);
		Billing billing = new Billing(1, 1, "planA");
		bh.addBilling(billing);
		assertEquals(billing, bh.getBillingList().get(0));
		assertEquals("Billing History: 1\nBillingId: 1\nUserId: 1\nPlan: planA\n", bh.toString());
		billing.setPlan("planB");
		assertEquals("Billing History: 1\nBillingId: 1\nUserId: 1\nPlan: planB\n", bh.toString());
	}
	
	@Test
	public void TestGetBillingId() {
		BillingHistory bh = new BillingHistory(1);
		Billing billing = new Billing(1, 1, "planA");
		bh.addBilling(billing);
		assertEquals("[1]", bh.getBillingIdList().toString());
	}
}
