package edu.rosehulman.billing;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.User;

public class RecurringBillJob implements Job {
	
	public static final String JOB_DATA_MAP_USER = "USER";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		User user = (User) context.getJobDetail().getJobDataMap().get(JOB_DATA_MAP_USER);
		Partner p = user.getPartner();
//		String webhook = p.getWebhook();
		String webhook = "need to be filled";
		
		HttpResponse<String> response;
		try {
			response = Unirest.post(webhook).asString();
			if (response.equals("200")) {
			}else {
				System.err.println("Error in Partner Server");
			}
		} catch (UnirestException e) {
			System.err.println("Failed to send request in RecurringBillJob");
			e.printStackTrace();
		}
	}

}
