package com.criticalgnome.payments.locale;

/**
 * @author CriticalGnome
 *
 */
public class LocaleFactory {
	public static String getLocale(String key) {
		String locale = null;
		LocalesTable entry = LocalesTable.valueOf(key.toUpperCase());
		locale = entry.getLocale();
		return locale;
	}

}
