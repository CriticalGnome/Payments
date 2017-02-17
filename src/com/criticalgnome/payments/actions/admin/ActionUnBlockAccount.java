package com.criticalgnome.payments.actions.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.dao.AccountDAO;

/**
 * @author CriticalGnome
 *
 */
public class ActionUnBlockAccount implements Action {
	public static final Logger logger = LogManager.getLogger(ActionUnBlockAccount.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			AccountDAO.getInstance().unBlockAccount(id);
			page = "controller?action=adminarea";
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			page = "error.jsp?reason=SQL Exception";
		}
		return page;
	}

}
