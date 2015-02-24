package com.stacksync.quotaserver.db;

import org.apache.log4j.Logger;

import com.stacksync.quotaserver.db.postgresql.PostgresqlConnectionPool;
import com.stacksync.quotaserver.exceptions.dao.DAOConfigurationException;
import com.stacksync.quotaserver.util.Config;

public class ConnectionPoolFactory {
	private static final Logger logger = Logger.getLogger(ConnectionPoolFactory.class.getName());

	public static ConnectionPool getConnectionPool(String datasource) throws DAOConfigurationException {

		if ("postgresql".equalsIgnoreCase(datasource)) {
			// Obtain info
			String host = Config.getPostgresqlHost();
			Integer port = Config.getPostgresqlPort();
			String database = Config.getPostgresqlDatabase();
			String password = Config.getPostgresqlPassword();
			String username = Config.getPostgresqlUsername();
			int initialConns = Config.getPostgresqlInitialConns();
			int maxConns = Config.getPostgresqlMaxConns();

			//
			return new PostgresqlConnectionPool(host, port, database, username, password, initialConns, maxConns);
		}

		logger.error("Could not find any driver matching your request");
		throw new DAOConfigurationException("Driver not found");

	}
}
