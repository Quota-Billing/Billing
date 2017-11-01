package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;

public class QuotaTest {
	
	@Test
	public void testSetAndGetId(){
		Quota q = new Quota();
		q.setId("2");
		assertEquals("2", q.getId());
	}
	
	@Test
	public void testSetAndGetName() {
		Quota q = new Quota();
		q.setName("quotaName");
		assertEquals("quotaName", q.getName());
	}
	
	@Test
	public void testSetAndGetType() {
		Quota q = new Quota();
		q.setType("time_recurring");
		assertEquals("time_recurring", q.getType());
	}
	
	@Test
	public void testSetAndGetTiers() {
		Tier tierMock = EasyMock.createMock(Tier.class);
		EasyMock.replay(tierMock);
		List<Tier> tiers = new ArrayList<Tier>();
		tiers.add(tierMock);
		
		Quota q = new Quota();
		q.setTiers(tiers);
		assertTrue(tiers.equals(q.getTiers()));
		
		EasyMock.verify(tierMock);
	}
	
	@Test
	public void testAddTier() {
		Tier tierMock = EasyMock.createMock(Tier.class);
		EasyMock.replay(tierMock);
		Quota q = new Quota();
		
		q.addTiers(tierMock);
		assertTrue(q.getTiers().contains(tierMock));
		
		EasyMock.verify(tierMock);
	}
	
	@Test
	public void testRemoveTier(){
		Tier tierMock = EasyMock.createMock(Tier.class);
		EasyMock.replay(tierMock);
		List<Tier> tiers = new ArrayList<Tier>();
		tiers.add(tierMock);
		
		Quota q = new Quota();
		q.setTiers(tiers);
		assertTrue(q.getTiers().contains(tierMock));
		q.removeTiers(tierMock);
		assertFalse(q.getTiers().contains(tierMock));
		
		EasyMock.verify(tierMock);
	}

	@Test
	public void testConstructor() {
		Quota q = new Quota("1", "a", "b");
		assertEquals("a", q.getName());
		assertEquals("b", q.getType());
		assertEquals("1", q.getId());
	}
	
	@Test
	public void testToString() {
		Quota q = new Quota("1", "quotaName", "time_recurring");
		assertEquals("Quota: 1\nName: quotaName\nType: time_recurring\nTiers: ", q.toString());
	}

}
