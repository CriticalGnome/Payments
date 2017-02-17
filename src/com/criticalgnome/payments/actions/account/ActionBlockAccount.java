package com.criticalgnome.payments.actions.account;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.dao.AccountDAO;

/**
 * @author CriticalGnome
 *
 */
public class ActionBlockAccount implements Action {
	private static final Logger logger = LogManager.getLogger(ActionBlockAccount.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		try {
			AccountDAO.getInstance().blockAccount(userID);
			page = "controller?action=userarea";
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			page = "error.jsp?reason=SQL Exception";
		}
		return page;
	}

}
