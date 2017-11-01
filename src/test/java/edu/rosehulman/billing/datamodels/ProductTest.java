package edu.rosehulman.billing.datamodels;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;

public class ProductTest {

	@Test
	public void testSetAndGetId() {
		Product p = new Product();

		p.setId("1");
		assertEquals("1", p.getId());
	}

	@Test
	public void testSetAndGetName() {
		Product p = new Product();

		p.setName("productName");
		assertEquals("productName", p.getName());
	}

	@Test
	public void testAddAndGetQuotas() {
		Quota quotaMock = EasyMock.createMock(Quota.class);
		EasyMock.expect(quotaMock.getId()).andStubReturn("2");
		EasyMock.replay(quotaMock);

		Product p = new Product();
		p.addQuota(quotaMock);
		assertEquals(quotaMock, p.getQuota("2"));

		EasyMock.verify(quotaMock);
	}

	@Test
	public void testRemoveQuota() {
		Quota quotaMock = EasyMock.createMock(Quota.class);
		EasyMock.expect(quotaMock.getId()).andStubReturn("2");
		EasyMock.replay(quotaMock);

		Product p = new Product();
		p.addQuota(quotaMock);
		assertEquals(quotaMock, p.getQuota("2"));

		p.removeQuota(quotaMock);
		assertEquals(null, p.getQuota("2"));

		EasyMock.verify(quotaMock);
	}

	@Test
	public void testConstructor() {
		Product p = new Product("1", "b");
		assertEquals("b", p.getName());
		assertEquals("1", p.getId());
	}

	@Test
	public void testToStringNoQuota() {
		Product p = new Product("1", "quotaName");
		assertEquals("Product: 1\nName: quotaName\nQuotas: ", p.toString());
	}

}
