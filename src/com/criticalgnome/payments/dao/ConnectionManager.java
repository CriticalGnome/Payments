package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.utils.ConfigParser;
import com.mysql.jdbc.Connection;

public class ConnectionManager {
	
	private static volatile ConnectionManager instance;
	private static final Logger logger = LogManager.getLogger(ConnectionManager.class);
	
	private ConnectionManager() {
		
	}
	
	/**
	 * Singleton
	 */
	public static ConnectionManager getInstance() {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null) {
					instance = new ConnectionManager();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = (Connection) DriverManager.getConnection(ConfigParser.getValue("dburl"), ConfigParser.getValue("dbuser"), ConfigParser.getValue("dbpassword"));
		} catch (SQLException | IOException e) {
			logger.log(Level.ERROR, "Can't estabish MySQL connection");
			e.printStackTrace();
		}
		return con;
		
	}

}
