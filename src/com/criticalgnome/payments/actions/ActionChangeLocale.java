package com.criticalgnome.payments.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Change current locale from 'ru' to 'en' and vice versa
 * @author CriticalGnome
 *
 */
public class ActionChangeLocale implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("locale") == null || session.getAttribute("locale").equals("locale_en_US")) {
			session.setAttribute("locale", "locale_ru_RU");
		} else {
			session.setAttribute("locale", "locale_en_US");
		}
		return request.getHeader("referer");
	}

}
