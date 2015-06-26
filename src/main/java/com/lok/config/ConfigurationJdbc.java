package com.lok.config;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class ConfigurationJdbc {

	private static final String DBURL = "jdbc:mysql://localhost/kplok_dev_db?user=root&password=root";
	public static BasicDataSource connectionPool;
	
	// Get connection from db pool
		public synchronized static void setPoolConnection() {
			
			if (connectionPool == null){
			connectionPool = new BasicDataSource();

			connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
			connectionPool.setUrl(DBURL);
			connectionPool.setInitialSize(4);
			connectionPool.setDefaultAutoCommit(false);
			}
		}
		
		// Close any open connection
		public synchronized static void closeOpenConnection() {

			// basic conn pool nullify
			if (connectionPool != null) {
				try {
					connectionPool.close();
				} catch (Exception e) {

				} finally {
					connectionPool = null;
				}
			}

		}
}
