package com.criticalgnome.payments.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author CriticalGnome
 * Parsing configuration file
 */
public class ConfigParser {
	
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
	public static String getValue(String key) throws IOException {
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_PATH));
		return prop.getProperty(key);
	}
}
