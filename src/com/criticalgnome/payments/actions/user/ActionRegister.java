package com.criticalgnome.payments.actions.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.beans.Account;
import com.criticalgnome.payments.beans.Card;
import com.criticalgnome.payments.dao.AccountDAO;
import com.criticalgnome.payments.dao.CardDAO;
import com.criticalgnome.payments.dao.UserDAO;
import com.criticalgnome.payments.utils.MD5;
import com.criticalgnome.payments.utils.NewCard;
import com.criticalgnome.payments.utils.RegExChecker;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * @author CriticalGnome
 *
 */
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
			if (!RegExChecker.checkWithRegExp(request.getParameter("firstName"))) {
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
			if (!RegExChecker.checkWithRegExp(request.getParameter("lastName"))) {
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
			if (!RegExChecker.checkWithRegExp(request.getParameter("password"))) {
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
//			Add user into database
			try {
				UserDAO.getInstance().addUser(request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"), MD5.encode(request.getParameter("password")));
			} catch (MySQLIntegrityConstraintViolationException e) {
				logger.log(Level.WARN, "Email \"{}\" already exits", request.getParameter("email"));
				page = "register.jsp?action=emailalreadyexist";
			} catch (SQLException e) {
				logger.log(Level.FATAL, "SQL Exception in add user area");
				page = "error.jsp?reason=SQL Exception";
			} catch (IOException e) {
				logger.log(Level.FATAL, "Input/Output Exception");
				page = "error.jsp?reason=Input/Output Exception";
			} catch (NoSuchAlgorithmException e) {
				logger.log(Level.FATAL, "MD5 encode exception");
				page = "error.jsp?reason=Password Encoding error";
			}
//			Get created user ID by email
			int userId = 0;
			try {
				userId = UserDAO.getInstance().getId(request.getParameter("email"));
			} catch (SQLException e) {
				logger.log(Level.FATAL, "SQL Exception in get ID area");
				page = "error.jsp?reason=Input/SQL Exception";
			}			
//			Get max account number
			int maxNumber = 0;
			try {
				maxNumber = AccountDAO.getInstance().getmaxAccountNumber();
			} catch (SQLException e) {
				logger.log(Level.FATAL, "SQL Exception in get max account number area");
				page = "error.jsp?reason=Input/SQL Exception";
			}
//			Create account for new user
			try {
				AccountDAO.getInstance().createNewAccount(maxNumber+1, userId);
			} catch (SQLException e) {
				logger.log(Level.FATAL, "SQL Exception in create account area");
				page = "error.jsp?reason=Input/SQL Exception";
			}
//			Get created account ID
			int accountId = 0;
			try {
				Account account = AccountDAO.getInstance().getAccount(userId);
				accountId = account.getId();
			} catch (SQLException e) {
				logger.log(Level.FATAL, "SQL Exception in get account area");
				page = "error.jsp?reason=Input/SQL Exception";
			}
//			Create card(s) for new Account
			Random random = new Random();
			int count = random.nextInt(2)+1;
			for (int i = 0; i < count; i++) {
				Card card = NewCard.createCard();
				try {
					CardDAO.getInstance().createCard(card, accountId);
				} catch (SQLException e) {
					logger.log(Level.FATAL, "SQL Exception in create card area");
					page = "error.jsp?reason=Input/SQL Exception";
				}
			}
			
		}
		return page;
	}

}
