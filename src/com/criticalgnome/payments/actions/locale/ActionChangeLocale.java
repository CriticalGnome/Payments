package com.criticalgnome.payments.actions.locale;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.criticalgnome.payments.actions.Action;

/**
 * Change current locale
 * @author CriticalGnome
 *
 */
public class ActionChangeLocale implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String locale = LocaleFactory.getLocale(request.getParameter("locale"));
		session.setAttribute("locale", locale);
		Cookie c = new Cookie("locale", locale);
		c.setMaxAge(60 * 60 * 24 * 30);
		response.addCookie(c);
		return request.getHeader("referer");
	}

}
