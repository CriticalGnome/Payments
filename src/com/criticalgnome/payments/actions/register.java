package com.criticalgnome.payments.actions;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.dao.UserDAO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger(register.class);
	
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
				logger.log(Level.WARN, "Incorrect character in First Name detected: \"{}\"", request.getParameter("firstName"));
			} 
			redirectUrl = redirectUrl + "firstName=" + URLEncoder.encode(request.getParameter("firstName"), "UTF-8") + "&";
		}
		if (request.getParameter("lastName").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "lnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("lastName"))) {
				isError = true;
				redirectUrl = redirectUrl + "lnError=wrong&";
				logger.log(Level.WARN, "Incorrect character in Last Name detected: \"{}\"", request.getParameter("lastName"));
			} 
			redirectUrl = redirectUrl + "lastName=" + URLEncoder.encode(request.getParameter("lastName"), "UTF-8") + "&";
		}
		if (request.getParameter("email").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "emError=empty&";
		} else {
			redirectUrl = redirectUrl + "email=" + URLEncoder.encode(request.getParameter("email"), "UTF-8") + "&";
		}
		if (request.getParameter("password").equals("")) {
			isError = true;
			redirectUrl = redirectUrl + "pwError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("password"))) {
				isError = true;
				redirectUrl = redirectUrl + "pwError=wrong&";
				logger.log(Level.WARN, "Incorrect character in Password detected: \"{}\"", request.getParameter("password"));
			} 
			redirectUrl = redirectUrl + "password=" + URLEncoder.encode(request.getParameter("password"), "UTF-8") + "&";
		}
		if (!isError) {
			redirectUrl = "index.jsp?action=newuser";
			try {
				UserDAO.getInstance().addUser(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("password"));
			} catch (MySQLIntegrityConstraintViolationException e) {
				logger.log(Level.WARN, "Email \"{}\" already exits", request.getParameter("email"));
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
