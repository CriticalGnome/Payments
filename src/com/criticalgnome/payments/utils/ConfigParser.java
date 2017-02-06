package com.criticalgnome.payments.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.dao.UserDAO;

/**
 * @author CriticalGnome
 * Parsing configuration file
 */
public class ConfigParser {
	
	private static final Logger logger = LogManager.getLogger(ConfigParser.class);
	
	/**
	 * Path to config
	 */
	public static final String CONFIG_PATH = "config.properties";
	
	/**
	 * Extract parameter from config
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static String getValue(String key) {
		Properties prop = new Properties();
		try {
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PATH));
		} catch (IOException e) {
			logger.log(Level.FATAL, "Can't read condig file");
		}
		return prop.getProperty(key);
	}
}
