package com.criticalgnome.payments.locale;

public enum LocalesTable {
	DEFAULT {{ locale="locale_en_US"; }},
	RU {{ locale="locale_ru_RU"; }},
	BY {{ locale="locale_by_BY"; }};
	String locale;
	public String getLocale() {
		return locale;
	}
}
