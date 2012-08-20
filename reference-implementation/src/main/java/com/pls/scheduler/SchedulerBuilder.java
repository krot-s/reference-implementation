package com.pls.scheduler;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Sheduled tasks builder.
 * 
 * @author User
 *
 */
public class SchedulerBuilder {
	private JobContext context = null;

	/**
	 * Create new job.
	 * @param job 
	 * @param name 
	 * @param group 
	 * @return self.
	 */
	public SchedulerBuilder createNewJob(Class<? extends Job> job, String name, String group) {
		context = new JobContext();
		context.setName(name);
		context.setTriggerName(name + "Trigger");
		context.setTriggerGroup(group + "Trigger");
		context.setGroup(group);
		context.setJob(job);
		return this;
	}

	/**
	 * Schedule job. 
	 * @param seconds 
	 * @return self.
	 */
	public SchedulerBuilder scheduleInSeconds(int seconds) {
		context.setSeconds(seconds);
		return this;
	}

	/**
	 * Reset configured job.
	 */
	public void reset() {
		context = null;
	}

	/**
	 * Shedule and start job.
	 */
	public void schedule() {
		JobDetail job = JobBuilder.newJob(context.getJob()).withIdentity(context.getName(), context.getGroup()).build();
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(context.getSeconds())
				.repeatForever();
		
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(context.getTriggerName(), context.getTriggerGroup()).startNow()
				.withSchedule(scheduleBuilder).build();
		SchedulerFactory schedFact = new StdSchedulerFactory();
		try {
			Scheduler sched = schedFact.getScheduler();
			sched.scheduleJob(job, trigger);
			sched.start();
		} catch (SchedulerException e) {
			throw new RuntimeException("Sheduler failure", e);
		}
	}

	/**
	 * Holds job context.
	 * @author User
	 *
	 */
	private static class JobContext {
		private String name;
		private String triggerName;
		private String triggerGroup;
		private String group;
		private Class<? extends Job> job;
		private int seconds;

		/**
		 * Get job name.
		 * @return job name.
		 */
		public String getName() {
			return name;
		}

		/**
		 * Set job name.
		 * @param name 
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * Get job group.
		 * @return job group.
		 */
		public String getGroup() {
			return group;
		}

		/**
		 * Set job group.
		 * @param group 
		 */
		public void setGroup(String group) {
			this.group = group;
		}

		/**
		 * Get job class.
		 * @return job class.
		 */
		public Class<? extends Job> getJob() {
			return job;
		}

		/**
		 * Set job class.
		 * @param job 
		 */
		public void setJob(Class<? extends Job> job) {
			this.job = job;
		}

		/**
		 * Get seconds. 
		 * @return seconds. 
		 */
		public int getSeconds() {
			return seconds;
		}

		/**
		 * Set seconds.
		 * @param seconds 
		 */
		public void setSeconds(int seconds) {
			this.seconds = seconds;
		}

		/**
		 * Get trigger name.
		 * @return trigger name.
		 */
		public String getTriggerName() {
			return triggerName;
		}

		/**
		 * Set trigger name.
		 * @param triggerName 
		 */
		public void setTriggerName(String triggerName) {
			this.triggerName = triggerName;
		}

		/**
		 * trigger group.
		 * @return trigger group.
		 */
		public String getTriggerGroup() {
			return triggerGroup;
		}

		/**
		 * Set trigger group.
		 * @param triggerGroup 
		 */
		public void setTriggerGroup(String triggerGroup) {
			this.triggerGroup = triggerGroup;
		}
	}
}