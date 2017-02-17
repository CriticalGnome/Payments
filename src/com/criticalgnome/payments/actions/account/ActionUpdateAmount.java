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

public class ActionUpdateAmount implements Action {
	private static final Logger logger = LogManager.getLogger(ActionUpdateAmount.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int amountAdd = Integer.parseInt(request.getParameter("amountAdd"));
		if (amountAdd <= 0) {
			page = "error.jsp?reason=Illegal Value";
			return page;
		}
		amount = amount + amountAdd;
		try {
			AccountDAO.getInstance().updateAmount(userID, amount);
			page = "controller?action=userarea";
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			page = "error.jsp?reason=SQL Exception";
		}
		return page;
	}

}
