package com.pls.scheduler;

import javax.ejb.Stateless;

/**
 * Scheduler example.
 * 
 * @author User
 *
 */
@Stateless
public class ShedulerExample {
	private static final int INTERVAL = 10;
	
	/**
	 * Start execution.
	 */
	public void run() {
		new SchedulerBuilder().createNewJob(JobExample.class, "PLSJob", "PLSGroup")
		.scheduleInSeconds(INTERVAL)
		.schedule();		
	}
}
