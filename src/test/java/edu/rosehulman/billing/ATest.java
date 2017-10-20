package edu.rosehulman.billing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.Tier;

public class ATest {

	@Test
	public void SimplePartnerTest() {
		Partner a = new Partner(1, "a");
		assertEquals("a", a.getName());
		assertEquals(1, a.getId());
		assertEquals("Partner: 1\n", a.toString());
	}

	@Test
	public void SimpleProductTest() {
		Product a = new Product(1, "b");
		assertEquals("b", a.getName());
		assertEquals(1, a.getId());
		assertEquals("Product: 1\n", a.toString());
	}

	@Test
	public void SimpleQuotaTest() {
		Quota a = new Quota(1, "a", "b");
		assertEquals("a", a.getName());
		assertEquals("b", a.getType());
		assertEquals(1, a.getId());
		assertEquals("Quota: 1\n", a.toString());
	}

	@Test
	public void SimpleTierTest() {
		Tier a = new Tier("1", "a", 1000, 2, 3);
		assertEquals("1", a.getId());
		assertEquals("a", a.getName());
		assertEquals(1000, a.getMax());
		assertEquals(2, a.getValue());
		assertEquals(3, a.getPrice(), 0.001);
	}

	@Test
	public void AddProductToPartnerTest() {
		Partner partner = new Partner(1, "a");
		Product product = new Product(1, "b");
		// Test original partner
		assertEquals("a", partner.getName());
		assertEquals(1, partner.getId());
		assertEquals("Partner: 1\n", partner.toString());
		// Test original product
		assertEquals("b", product.getName());
		assertEquals(1, product.getId());
		assertEquals("Product: 1\n", product.toString());
		// Test add product to partner
		partner.addProduct(product);
		assertEquals(product, partner.getProduct(1));
		assertEquals("Partner: 1\nProduct: 1\n", partner.toString());
	}

	@Test
	public void AddQuotaToProduct() {
		Product product = new Product(1, "b");
		// Test original product
		assertEquals("b", product.getName());
		assertEquals(1, product.getId());
		assertEquals("Product: 1\n", product.toString());
		// Test original quota
		Quota quota = new Quota(1, "a", "b");
		assertEquals("a", quota.getName());
		assertEquals("b", quota.getType());
		assertEquals(1, quota.getId());
		assertEquals("Quota: 1\n", quota.toString());
		// Test add quota to product
		product.addQuota(quota);
		assertEquals("Product: 1\nQuota: 1\n", product.toString());
	}

	@Test
	public void AddTierToQuota() {
		// Test original quota
		Quota quota = new Quota(1, "a", "b");
		assertEquals("a", quota.getName());
		assertEquals("b", quota.getType());
		assertEquals(1, quota.getId());
		assertEquals("Quota: 1\n", quota.toString());
		// Test original tier
		Tier tier = new Tier("1", "a", 1000, 2, 3);
		assertEquals("1", tier.getId());
		assertEquals("a", tier.getName());
		assertEquals(1000, tier.getMax());
		assertEquals(2, tier.getValue());
		assertEquals(3, tier.getPrice(), 0.001);
		// Test add tier to quota
		ArrayList<Tier> tiers = new ArrayList<Tier>();
		tiers.add(tier);
		quota.setTiers(tiers);
		assertEquals(tiers, quota.getTiers());
	}

	@Test
	public void TestPartnerProductQuotaAndTier() {
		Partner partner = new Partner(1, "a");
		Product product = new Product(1, "b");
		// Test original partner
		assertEquals("a", partner.getName());
		assertEquals(1, partner.getId());
		assertEquals("Partner: 1\n", partner.toString());
		// Test original product
		assertEquals("b", product.getName());
		assertEquals(1, product.getId());
		assertEquals("Product: 1\n", product.toString());
		// Test original quota
		Quota quota = new Quota(1, "a", "b");
		assertEquals("a", quota.getName());
		assertEquals("b", quota.getType());
		assertEquals(1, quota.getId());
		assertEquals("Quota: 1\n", quota.toString());
		// Test original tier
		Tier tier = new Tier("1", "a", 1000, 2, 3);
		assertEquals("1", tier.getId());
		assertEquals("a", tier.getName());
		assertEquals(1000, tier.getMax());
		assertEquals(2, tier.getValue());
		assertEquals(3, tier.getPrice(), 0.001);
		//Add tier to quota, quota to product, product to partner.
		ArrayList<Tier> tiers = new ArrayList<Tier>();
		tiers.add(tier);
		quota.setTiers(tiers);
		assertEquals(tiers, quota.getTiers());
		product.addQuota(quota);
		System.out.println(product.toString());
		assertEquals("Product: 1\nQuota: 1\nTier: 1\n", product.toString());
		partner.addProduct(product);
		assertEquals(product, partner.getProduct(1));
		assertEquals("Partner: 1\nProduct: 1\nQuota: 1\nTier: 1\n", partner.toString());
	}
}
