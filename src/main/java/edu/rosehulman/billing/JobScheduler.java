package edu.rosehulman.billing;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;

import org.quartz.JobDetail;

import edu.rosehulman.billing.models.Partner;
import edu.rosehulman.billing.models.User;

public class JobScheduler {
	private Scheduler scheduler;

	public JobScheduler() {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			this.scheduler = factory.getScheduler();
			this.scheduler.start();
		} catch (SchedulerException e) {
			System.err.println("Fail to create a schedulre");
			e.printStackTrace();
		}
	}

	public void addJob(User user) {
		JobDetail job = newJob(RecurringBillJob.class).withIdentity(user.getId(), user.getPartner().getId()).build();
		job.getJobDataMap().put(RecurringBillJob.JOB_DATA_MAP_USER, user);

		//trigger job at 10am on 1st of every month
		Trigger trigger = newTrigger().withIdentity(user.getId(), user.getPartner().getId())
				.withSchedule(cronSchedule("0 0 10am 1 * ?")).build();
		
//		Trigger trigger = newTrigger().withIdentity(user.getId(), user.getPartner().getId())
//				.withSchedule(cronSchedule("0/10 * * * * ?")).build();   for demo,  job repeated very 10 sec

		try {
			this.scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			System.err.println("Fail to add job");
			e.printStackTrace();
		}
	}

	public void shutDown() {
		try {
			this.scheduler.shutdown(true);
		} catch (SchedulerException e) {
			System.err.println("Fail to shutdown the scheduler");
			e.printStackTrace();
		}
	}


// for testing scheduler locally
	public static void main(String[] args) {
		JobScheduler js = new JobScheduler();
		Partner p = new Partner("fakeId", null, null);
		User user = new User("fakeID");
		user.setPartner(p);
		js.addJob(user);
	}
}
