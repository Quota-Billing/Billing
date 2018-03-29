package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;

public class PartnerTest {

	@Test
	public void testSetAndGetId() {
		Partner p = new Partner();
		p.setId("1");
		assertEquals("1", p.getId());
	}

	@Test
	public void testSetAndGetName() {
		Partner p = new Partner();
		p.setName("partnerName");
		assertEquals("partnerName", p.getName());
	}

	@Test
	public void testAddAndGetProduct() {
		Product productMock = EasyMock.createMock(Product.class);
		EasyMock.expect(productMock.getId()).andStubReturn("1");
		EasyMock.replay(productMock);

		Partner p = new Partner();
		p.addProduct(productMock);
		assertEquals(productMock, p.getProduct("1"));

		EasyMock.verify(productMock);
	}

	@Test
	public void testRemoveProduct() {
		Product productMock = EasyMock.createMock(Product.class);
		EasyMock.expect(productMock.getId()).andStubReturn("1");
		EasyMock.replay(productMock);

		Partner p = new Partner();
		p.addProduct(productMock);
		assertEquals(productMock, p.getProduct("1"));
		p.removeProduct(productMock);
		assertEquals(null, p.getProduct("1"));

		EasyMock.verify(productMock);
	}

	@Test
	public void testConstructor() {
		Partner a = new Partner("1", "a", "apiKey");
		assertEquals("a", a.getName());
		assertEquals("1", a.getId());
		assertEquals("apiKey", a.getApiKey());
	}

}
