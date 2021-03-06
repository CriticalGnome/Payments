package com.criticalgnome.payments.actions.payment;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.dao.PaymentDAO;

/**
 * @author CriticalGnome
 *
 */
public class ActionSendFunds implements Action {
	private static final Logger logger = LogManager.getLogger(ActionSendFunds.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = null;
		if (request.getParameter("toAccount") == null) {
			page = "error.jsp?reason=No user selected";
			return page;
		}
		if (Integer.parseInt(request.getParameter("amount")) <= 0) {
			page = "error.jsp?reason=Illegal Value";
			return page;
		}
		System.out.println(request.getParameter("toAccount"));
		int fromAccount = Integer.parseInt(request.getParameter("fromAccount"));
		int toAccount = Integer.parseInt(request.getParameter("toAccount"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		String comment = request.getParameter("comment");
		try {
			PaymentDAO.getInstance().makePayment(fromAccount, toAccount, amount, comment);
			page = "controller?action=userarea";
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			page = "error.jsp?reason=SQL Exception";
		}
		return page;
	}

}
