package com.criticalgnome.payments.utils;

import java.util.Calendar;
import java.util.Random;

import com.criticalgnome.payments.beans.Card;

/**
 * @author CriticalGnome
 * Create New Card
 */
public class NewCard {
	static final Random random = new Random();
	static Calendar calendar = Calendar.getInstance();
	
	/**
	 * Emulating example Card data
	 * @return
	 */
	public static Card createCard() {
		String number = "";
		String month = "";
		String year = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				number += random.nextInt(9);
			}
			number += "-";
		}
		number = number.substring(0, 19);
		month += calendar.get(Calendar.MONTH)+1;
		if (month.length() == 1) { month = "0" + month; }
		year += calendar.get(Calendar.YEAR)+1;
		year = year.substring(2, 4);
		Card card = new Card.Builder()
				.number(number)
				.expMonth(month)
				.expYear(year)
				.build();
		return card;
	}
}
