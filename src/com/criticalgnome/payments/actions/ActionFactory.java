package com.criticalgnome.payments.actions;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

	public static Action getAction(HttpServletRequest request) {
		Action action;
		switch ((request.getParameter("action")).toUpperCase()) {
		case "LOGIN":
			action = new ActionLogin();
			break;
		case "REGISTER":
			action = new ActionRegister();
			break;
		default:
			action = null;
			break;
		}
		return action;
	}
}
