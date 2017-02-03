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
		String redirectUrl = "register.jsp?";

		if (request.getParameter("firstName").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "fnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("firstName"))) {
				isError = true;
				redirectUrl = redirectUrl + "fnError=wrong&";
			} 
			redirectUrl = redirectUrl + "firstName=" + URLEncoder.encode(request.getParameter("firstName")) + "&";
		}
		if (request.getParameter("lastName").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "lnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("lastName"))) {
				isError = true;
				redirectUrl = redirectUrl + "lnError=wrong&";
			} 
			redirectUrl = redirectUrl + "lastName=" + URLEncoder.encode(request.getParameter("lastName")) + "&";
		}
		if (request.getParameter("email").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "emError=empty&";
		} else {
			redirectUrl = redirectUrl + "email=" + URLEncoder.encode(request.getParameter("email")) + "&";
		}
		if (request.getParameter("password").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "pwError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("password"))) {
				isError = true;
				redirectUrl = redirectUrl + "pwError=wrong&";
			} 
			redirectUrl = redirectUrl + "password=" + URLEncoder.encode(request.getParameter("password")) + "&";
		}
		if (!isError) {
			redirectUrl = "index.jsp?action=newuser";
			try {
				UserDAO.getInstance().addUser(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("password"));
			} catch (MySQLIntegrityConstraintViolationException e) {
				redirectUrl = "register.jsp?action=emailalreadyexist";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect(redirectUrl);
	}
	public static boolean checkWithRegExp(String userNameString){  
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }
	
}
