package com.criticalgnome.payments.actions.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.beans.BlockedAccount;
import com.criticalgnome.payments.dao.AccountDAO;

/**
 * @author CriticalGnome
 *
 */
public class ActionAdminarea implements Action {
	private static final Logger logger = LogManager.getLogger(ActionAdminarea.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BlockedAccount> blockedAccounts = new ArrayList<BlockedAccount>();
		try {
			blockedAccounts = AccountDAO.getInstance().getBlockedAccounts();
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
		}
		request.setAttribute("blockedAccounts", blockedAccounts);
		request.getRequestDispatcher("adminarea.jsp").forward(request, response);
		
		return null;
	}

}
