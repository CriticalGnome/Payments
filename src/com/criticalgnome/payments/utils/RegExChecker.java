package com.criticalgnome.payments.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CriticalGnome
 * RegEx Checker
 */
public class RegExChecker {

	/**
	 * Check input with Pattern
	 * @param userNameString
	 * @return
	 */
	public static boolean checkWithRegExp(String userNameString){  
	    Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");  
	    Matcher m = p.matcher(userNameString);  
	    return m.matches();  
	}

}
