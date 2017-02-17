package com.criticalgnome.payments.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Action Interface
 * @author CriticalGnome
 *
 */
public interface Action {
	/**
	 * Return target page
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
