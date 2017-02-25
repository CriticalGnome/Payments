package com.criticalgnome.payments.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CriticalGnome
 *
 */
public class ActionFactory {

	/**
	 * Return current Action based on 'action' parameter
	 * @param request
	 * @param responce
	 * @return
	 */
	public static Action getAction(HttpServletRequest request, HttpServletResponse responce) {
		Action action = null;
		ActionsTable entry = ActionsTable.valueOf(request.getParameter("action").toUpperCase()); 
		action = entry.getCommand();
		return action;
	}
}
