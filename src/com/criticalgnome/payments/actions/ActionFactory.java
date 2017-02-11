package com.criticalgnome.payments.actions;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

	public static Action getAction(HttpServletRequest request) {
		Action action = null;
		ActionsTable entry = ActionsTable.valueOf(request.getParameter("action").toUpperCase()); 
		action = entry.getCommand();
		return action;
	}
}
