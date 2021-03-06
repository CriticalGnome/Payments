package com.criticalgnome.payments.actions.user;

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
import com.criticalgnome.payments.beans.AvailableForPayment;
import com.criticalgnome.payments.beans.Card;
import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.dao.AccountDAO;
import com.criticalgnome.payments.dao.CardDAO;
import com.criticalgnome.payments.dao.UserDAO;

/**
 * @author CriticalGnome
 *
 */
public class ActionUserarea implements Action {
	private static final Logger logger = LogManager.getLogger(ActionUserarea.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		User user = null;
		Account account = null;
		List<Card> cards = new ArrayList<Card>();
		List<AvailableForPayment> availableAccounts = new ArrayList<AvailableForPayment>();
		try {
			user = UserDAO.getInstance().getUser(userID);
			account = AccountDAO.getInstance().getAccount(userID);
			cards = CardDAO.getInstance().getCards(userID);
			availableAccounts = AccountDAO.getInstance().getAllAvailableAccounts();
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
			return "error.jsp?reason=SQL Exception";
		}
		request.setAttribute("user", user);
		request.setAttribute("account", account);
		request.setAttribute("cards", cards);
		request.setAttribute("availableAccounts", availableAccounts);
		request.getRequestDispatcher("userarea.jsp").forward(request, response);
		
		return null;
	}

}
