package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.rosehulman.billing.models.Tier;

public class TierTest {

	@Test
	public void testSetAndGetId() {
		Tier t = new Tier();
		t.setId("1");
		assertEquals("1", t.getId());
	}

	@Test
	public void testSetAndGetName() {
		Tier t = new Tier();
		t.setName("tierName");
		assertEquals("tierName", t.getName());
	}

	@Test
	public void testSetAndGetMax() {
		Tier t = new Tier();
		t.setMax(500);
		assertEquals(500, t.getMax());
	}

	@Test
	public void testSetAndGetValue() {
		Tier t = new Tier();
		t.setValue(400);
		assertEquals(400, t.getValue());
	}

	@Test
	public void testSetAndGetPrice() {
		Tier t = new Tier();
		t.setPrice(5.5);
		assertEquals(5.5, t.getPrice(), 0.01);
	}

	@Test
	public void testConstructor() {
		Tier t = new Tier("1", "a", 1000, 3, 0);
		assertEquals("1", t.getId());
		assertEquals("a", t.getName());
		assertEquals(1000, t.getMax());
		assertEquals(3, t.getPrice(), 0.001);
	}

	@Test
	public void testToString() {
		Tier t = new Tier("1", "name", 1000,3, 0);
		assertEquals("Tier: 1\n", t.toString());
	}
}
