package com.criticalgnome.payments.actions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.criticalgnome.payments.dao.UserDAO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		Boolean isError = false;
		String stringError = "register.jsp?";

		if (request.getParameter("firstName").equals("")) {
			isError = true;
			stringError = stringError + "fnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("firstName"))) {
				isError = true;
				stringError = stringError + "fnError=wrong&";
			} 
			stringError = stringError + "firstName=" + URLEncoder.encode(request.getParameter("firstName")) + "&";
		}
		if (request.getParameter("lastName").equals("")) {
			isError = true;
			stringError = stringError + "lnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("lastName"))) {
				isError = true;
				stringError = stringError + "lnError=wrong&";
			} 
			stringError = stringError + "lastName=" + URLEncoder.encode(request.getParameter("lastName")) + "&";
		}
		if (request.getParameter("email").equals("")) {
			isError = true;
			stringError = stringError + "emError=empty&";
		} else {
			stringError = stringError + "email=" + URLEncoder.encode(request.getParameter("email")) + "&";
		}
		if (request.getParameter("password").equals("")) {
			isError = true;
			stringError = stringError + "pwError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("password"))) {
				isError = true;
				stringError = stringError + "pwError=wrong&";
			} 
			stringError = stringError + "password=" + URLEncoder.encode(request.getParameter("password")) + "&";
		}
		if (isError) {
			response.sendRedirect(stringError);
		}
		// No Errors, adding user to DB
		String defaultRedirect = "index.jsp?action=newuser";
		try {
			UserDAO.getInstance().addUser(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("password"));
		} catch (MySQLIntegrityConstraintViolationException e) {
			defaultRedirect = "register.jsp?action=emailalreadyexist";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(defaultRedirect);
	}
	public static boolean checkWithRegExp(String userNameString){  
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }
	
}
