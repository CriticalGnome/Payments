package com.criticalgnome.payments.actions;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	String execute(HttpServletRequest request);
}
