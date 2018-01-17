package edu.rosehulman.billing;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.Quota;
import edu.rosehulman.billing.models.User;

public class RecurringBillJob implements Job {
	
	public static final String JOB_DATA_MAP_USER = "USER";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		User user = (User) context.getJobDetail().getJobDataMap().get(JOB_DATA_MAP_USER);
		Partner p = user.getPartner();
		
		JSONObject billJson = new JSONObject();
		Map<String, String> billDetail = new HashMap<String,String>();
		
//		for (Quota quota : user.getProduct().getQuotas()) {
//			if (quota.getType().equals("recurring")) {
//				String price = quota.getTiers().get(0).getPrice()+"";   // need to know what tier the user is
//				billDetail.put(quota.getName(), price);
//			}
//		}
		billJson.put("detail", billDetail);
		
//		String webhook = p.getWebhook();
		String webhook = "http://localhost:8080/recurringBill";
		
		try {
			int response = Unirest.post(webhook).body(user.getId()).asString().getStatus();
			if (response == 200) {
				System.out.println("Bill sent for " + user.getId() +" !");
			}else {
				System.err.println("Error in Partner server");
			}
		} catch (UnirestException e) {
			System.err.println("Failed to send request in RecurringBillJob");
			e.printStackTrace();
		}
	}

}
