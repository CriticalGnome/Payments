package com.criticalgnome.payments.actions.payment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.actions.Action;
import com.criticalgnome.payments.beans.Account;
import com.criticalgnome.payments.beans.Payment;
import com.criticalgnome.payments.dao.AccountDAO;
import com.criticalgnome.payments.dao.PaymentDAO;
import com.criticalgnome.payments.utils.ConfigParser;

public class ActionGetPayments implements Action {
	private static final Logger logger = LogManager.getLogger(ActionGetPayments.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		Account account = null;
		try {
			account = AccountDAO.getInstance().getAccount(userID);
		} catch (SQLException e1) {
			logger.log(Level.FATAL, "SQL Exception");
		}
		List<Payment> payments = new ArrayList<Payment>();
		try {
			payments = PaymentDAO.getInstance().getPayments(account.getId());
		} catch (SQLException e2) {
			logger.log(Level.FATAL, "SQL Exception");
		}
		int paymentsCount = payments.size();
		int maxRecords = Integer.parseInt(ConfigParser.getValue("paymentslistmaxrecords"));
		request.setAttribute("accountID", account.getId());
		request.setAttribute("maxRecords", maxRecords);
		request.setAttribute("payments", payments);
		request.setAttribute("paymentsCount", paymentsCount);
		request.getRequestDispatcher("payments.jsp").forward(request, response);
		
		return null;
	}

}
