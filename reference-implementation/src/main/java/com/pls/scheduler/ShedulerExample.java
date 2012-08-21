package com.pls.scheduler;


/**
 * Scheduler example.
 * 
 * @author User
 *
 */
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
