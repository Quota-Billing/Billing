package edu.rosehulman.billing.datastore;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import edu.rosehulman.billing.Database;
import edu.rosehulman.billing.models.Partner;

public class PartnerDatastore {
	Datastore datastore;

	public PartnerDatastore(Datastore ds) {
		this.datastore = ds;
	}

	public String addPartnerDirect(Partner partner) {
		try {
			datastore.save(partner);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}
	
	public String deletePartnerDirect(Partner partner) {
		try {
			Query<Partner> query1 = datastore.createQuery(Partner.class);
			query1.field("partnerId").equal(partner.getId());
			datastore.findAndDelete(query1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String addPartner(String partnerId, String name, String apiKey, String password) {
		try {
			Partner partner = new Partner(partnerId, name, apiKey);
			partner.setPassword(password);
			this.datastore.save(partner);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return "ok";
	}

	public String updatePartner(Partner partner) {
		Query<Partner> query = this.datastore.createQuery(Partner.class).field("partnerId").equal(partner.getId());
		List<Partner> list = query.asList();
		System.out.println("#partner: " + list.size());
		System.out.println(partner.getWebhook());
		UpdateOperations<Partner> op = this.datastore.createUpdateOperations(Partner.class).set("webhook",
				partner.getWebhook());
		this.datastore.update(query, op);
		return "ok";
	}

	public Partner getPartner(String partnerId) {
		List<Partner> partners = datastore.createQuery(Partner.class).field("partnerId").equal(partnerId).asList();
		if (partners.size() == 0) {
			System.out.println("wrong partnerId"); // debugging
			return null;
		}
		Partner partner = partners.get(0);
		return partner;
	}
}
