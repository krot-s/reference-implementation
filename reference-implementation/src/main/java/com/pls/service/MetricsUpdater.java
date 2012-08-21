package com.pls.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Update metrics statistics.
 * 
 * @author User
 * 
 */
@Stateless
public class MetricsUpdater {
	private static final Logger LOG = LoggerFactory.getLogger(MetricsUpdater.class);

	private static String updateMetricsQuery;

	@Inject
	private EntityManager em;

	static {
		BufferedReader bufReader = null;
		try {
			InputStream stream = MetricsUpdater.class.getClassLoader().getResourceAsStream("/update_metrics.sql");
			InputStreamReader reader = new InputStreamReader(stream);
			bufReader = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();

			while (bufReader.ready()) {
				builder.append(bufReader.readLine());
			}
			updateMetricsQuery = builder.toString();
		} catch (FileNotFoundException e) {
			LOG.error("", e);
		} catch (IOException e) {
			LOG.error("", e);
		} finally {
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					LOG.error("", e);
				}
			}
		}
	}

	/**
	 * Update metrics.
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateMetrics() {
		String queryString = "delete from freight_solns_metrics";
		Query query = em.createNativeQuery(queryString);
		query.executeUpdate();

		query = em.createNativeQuery(updateMetricsQuery);
		query.executeUpdate();
	}
}
