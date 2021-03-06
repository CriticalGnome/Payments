package com.criticalgnome.payments.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.utils.ConfigParser;

/**
 * @author CriticalGnome
 *
 */
public class ConnectionPool {

	private static volatile ConnectionPool instance;
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

	private BlockingQueue<Connection> queue;
    private String url;
    private String user;
    private String password;
    private String driver;
    private int maxConnections;
    
    private ConnectionPool(){
    	url = ConfigParser.getValue("dburl");
    	user = ConfigParser.getValue("dbuser");
    	password = ConfigParser.getValue("dbpassword");
    	driver = ConfigParser.getValue("dbdriver");
    	maxConnections = Integer.parseInt(ConfigParser.getValue("dbmaxconnections"));
    	queue = new ArrayBlockingQueue<>(maxConnections);
    }
	
	/**
	 * Singleton
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}
    
    /**
     * Return connection
     * @return
     */
    public synchronized Connection getConnection() throws SQLException {
    	Connection connection = null;
    	if (queue.isEmpty()) {
    		createConnection();
    	}
    	try {
			connection = queue.take();
		} catch (InterruptedException e) {
			logger.log(Level.FATAL, "ConnectionPool Queue error");
		}
		return connection;
    }
    
    /**
     * Add used connection to Queue
     * @param connection
     */
    public synchronized void releaseConnection(Connection connection) {
    	queue.add(connection);
    }
    
    /**
     * Create new connection
     */
    public void createConnection() throws SQLException {
    	if (queue.size() < maxConnections) {
    		try {
				Class.forName(driver);
				Connection connection = DriverManager.getConnection(url, user, password);
				queue.put(connection);
				
			} catch (ClassNotFoundException e) {
				logger.log(Level.FATAL, "Unexpected internal error");
			} catch (SQLException e) {
				throw e;
			} catch (InterruptedException e) {
				logger.log(Level.FATAL, "ConnectionPool Queue error");
			}
    	}
    }
}
