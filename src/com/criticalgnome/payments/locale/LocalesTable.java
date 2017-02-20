package com.criticalgnome.payments.locale;

public enum LocalesTable {
	DEFAULT {{ locale="locale_en_US"; }},
	RU {{ locale="locale_ru_RU"; }};
	String locale;
	public String getLocale() {
		return locale;
	}
}
