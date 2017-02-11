package com.criticalgnome.payments.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.dao.UserDAO;

public class ActionLogin implements Action {
	private static final Logger logger = LogManager.getLogger(ActionLogin.class);


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		try {
			User user = UserDAO.getInstance().getUser(request.getParameter("email"), request.getParameter("password"));
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("isAuthorized", "yes");
				session.setAttribute("userID", user.getId());
				session.setAttribute("role", user.getRole());
				logger.log(Level.INFO, "User {} [id={}] get login by role {})", user.getEmail(), user.getId(), user.getRole());
				page = "index.jsp?action=login";
			} else {
				logger.log(Level.WARN, "Incorrect user data (email=\"{}\", password=\"{}\")", request.getParameter("email"), request.getParameter("password"));
				page = "login.jsp?action=wronglogin";
			}
		} catch (SQLException e) {
			logger.log(Level.FATAL, "Can't access to database");
			page = "error.jsp?reason=MySQL Access Error";
		} catch (IOException e) {
			logger.log(Level.FATAL, "Input/Output error");
			page = "error.jsp?reason=Input/Output Error";
		}
		return page;
	}

}
