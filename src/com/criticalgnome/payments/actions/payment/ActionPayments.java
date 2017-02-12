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
import com.criticalgnome.payments.beans.Payment;
import com.criticalgnome.payments.dao.PaymentDAO;

public class ActionPayments implements Action {
	private static final Logger logger = LogManager.getLogger(ActionPayments.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		List<Payment> payments = new ArrayList<Payment>();
		try {
			payments = PaymentDAO.getInstance().getPayments(userID);
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
		}
		int paymentsCount = payments.size();
		request.setAttribute("payments", payments);
		request.setAttribute("paymentsCount", paymentsCount);
		request.getRequestDispatcher("payments.jsp").forward(request, response);
		
		return null;
	}

}
