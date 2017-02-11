package com.criticalgnome.payments.actions.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.dao.UserDAO;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class ActionRegister implements Action {
	private static final Logger logger = LogManager.getLogger(ActionRegister.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean isError = false;
		String page = "register.jsp?";

		if (request.getParameter("firstName").equals("")) {
			isError = true;
			page = page + "fnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("firstName"))) {
				isError = true;
				page = page + "fnError=wrong&";
				logger.log(Level.WARN, "Incorrect character in First Name detected: \"{}\"", request.getParameter("firstName"));
			} 
			try {
				page = page + "firstName=" + URLEncoder.encode(request.getParameter("firstName"), "UTF-8") + "&";
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.ERROR, "Unsupported encoding");
			}
		}
		if (request.getParameter("lastName").equals("")) {
			isError = true;
			page = page + "lnError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("lastName"))) {
				isError = true;
				page = page + "lnError=wrong&";
				logger.log(Level.WARN, "Incorrect character in Last Name detected: \"{}\"", request.getParameter("lastName"));
			} 
			try {
				page = page + "lastName=" + URLEncoder.encode(request.getParameter("lastName"), "UTF-8") + "&";
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.ERROR, "Unsupported encoding");
			}
		}
		if (request.getParameter("email").equals("")) {
			isError = true;
			page = page + "emError=empty&";
		} else {
			try {
				page = page + "email=" + URLEncoder.encode(request.getParameter("email"), "UTF-8") + "&";
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.ERROR, "Unsupported encoding");
			}
		}
		if (request.getParameter("password").equals("")) {
			isError = true;
			page = page + "pwError=empty&";
		} else {
			if (!checkWithRegExp(request.getParameter("password"))) {
				isError = true;
				page = page + "pwError=wrong&";
				logger.log(Level.WARN, "Incorrect character in Password detected: \"{}\"", request.getParameter("password"));
			} 
			try {
				page = page + "password=" + URLEncoder.encode(request.getParameter("password"), "UTF-8") + "&";
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.ERROR, "Unsupported encoding");
			}
		}
		if (!isError) {
			page = "index.jsp?action=newuser";
			try {
				UserDAO.getInstance().addUser(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), request.getParameter("password"));
			} catch (MySQLIntegrityConstraintViolationException e) {
				logger.log(Level.WARN, "Email \"{}\" already exits", request.getParameter("email"));
				page = "register.jsp?action=emailalreadyexist";
			} catch (SQLException e) {
				logger.log(Level.FATAL, "Can't access to database");
			} catch (IOException e) {
				logger.log(Level.FATAL, "Input/Output Exception");
				page = "error.jsp?reason=Input/Output Exception";
			}
		}
		return page;
	}
	public static boolean checkWithRegExp(String userNameString){  
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z0-9]+$");  
        Matcher m = p.matcher(userNameString);  
        return m.matches();  
    }

}
