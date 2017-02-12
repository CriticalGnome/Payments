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
import com.criticalgnome.payments.dao.PaymentDAO;

public class ActionSendFunds implements Action {
	private static final Logger logger = LogManager.getLogger(ActionSendFunds.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		HttpSession session = request.getSession();
		int fromAccount = (int) session.getAttribute("userID"); 
		int toAccount = Integer.parseInt(request.getParameter("id"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String comment = request.getParameter("comment");
		try {
			PaymentDAO.getInstance().makePayment(fromAccount, toAccount, amount, comment);
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			page = "error.jsp?reason=SQL Exception";
		}
		page = "controller?action=userarea";
		return page;
	}

}
