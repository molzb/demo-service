package com.syniverse.demo.util;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronJob {

	@Scheduled(cron = "20 * * * * MON-FRI")
	public void createUpdatesEveryHours() {
		System.out.println("This is how you build a cron-job that fires every minute at the 20s mark " + new Date());
	}
}
