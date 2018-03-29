package edu.rosehulman.billing.router;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.rosehulman.billing.NewBillingServer;

@RunWith(Suite.class)
@SuiteClasses({ AddBillingHandlerTest.class, QuotaReachedHandlerTest.class })
public class HandlerTestSuite {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new Thread(() -> {
			try {
				ExecutorService executor = Executors.newSingleThreadExecutor();
				executor.submit(() -> {
					try {
						NewBillingServer.main(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				executor.awaitTermination(5, TimeUnit.MINUTES);
				executor.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		Thread.sleep(3000);
	}
}
