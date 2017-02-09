package com.criticalgnome.payments.actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page;
		if (request.getParameter("action") == null) {
			page = "error.jsp?reason=Illegal Call";
		} else {
			Action action = ActionFactory.getAction(request);
			if (action != null) {
				page = action.execute(request);
			} else {
				page = "error.jsp?reason=Illegal Call";
			}
		}
		response.sendRedirect(page);
	}

}