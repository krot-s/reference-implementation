package com.pls.scheduler;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;

import com.pls.service.MetricsUpdater;

/**
 * Example job.
 * 
 * @author User
 *
 */
public class JobExample implements Job {
	private static final String EJB_JNDI_NAME = 
			"java:global/reference-implementation/MetricsUpdater!com.pls.service.MetricsUpdater";

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		Context initContext;
		try {
			initContext = new InitialContext();
			MetricsUpdater bean = (MetricsUpdater) initContext.lookup(EJB_JNDI_NAME);
			bean.updateMetrics();
			LoggerFactory.getLogger(JobExample.class).info("Job executed");
		} catch (NamingException e) {
			LoggerFactory.getLogger(JobExample.class).error("", e);
		}
	}
}