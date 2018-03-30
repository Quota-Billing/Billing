package edu.rosehulman.billing.datastore;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Product;

public class ProductDatastore {
	Datastore datastore;

	public ProductDatastore(Datastore ds) {
		datastore = ds;
	}

	public String addProductToPartner(String partnerId, String name, String productId) {
		try {
			Partner partner = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList().get(0);
			Product product = new Product(productId, name);
			partner.addProduct(product);
			this.datastore.save(product);
			Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partnerId);
			UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).push("products",
					product);
			this.datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addProductDirect(Product product, Partner partner) {
		try {
			datastore.save(product);
			Query<Partner> query = datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
			UpdateOperations<Partner> op = datastore.createUpdateOperations(Partner.class).push("products",
					product);
			datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String deleteProductDirect(Product product, Partner partner) {
		try {
			Query<Product> query1 = datastore.createQuery(Product.class);
			query1.field("productId").equal(product.getId());
			datastore.findAndDelete(query1);

			Query<Partner> query = datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
			UpdateOperations<Partner> op = datastore.createUpdateOperations(Partner.class).removeAll("products",
					product);
			datastore.update(query, op);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

}
