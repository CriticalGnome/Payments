package com.criticalgnome.payments.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author CriticalGnome
 * MD5 encoding
 */
public class MD5 {
	/**
	 * Encode input String to MD5
	 * @param input
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(String input) throws NoSuchAlgorithmException {
	    String result = input;
	    if(input != null) {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(input.getBytes());
	        BigInteger hash = new BigInteger(1, md.digest());
	        result = hash.toString(16);
	        while(result.length() < 32) {
	            result = "0" + result;
	        }
	    }
	    return result;
	}
}
