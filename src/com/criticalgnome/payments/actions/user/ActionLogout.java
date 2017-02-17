package com.criticalgnome.payments.actions.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.criticalgnome.payments.actions.Action;

/**
 * @author CriticalGnome
 *
 */
public class ActionLogout implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("isAuthorized");
		session.removeAttribute("userID");
		session.removeAttribute("role");
		return "index.jsp?action=logout";
	}

}
