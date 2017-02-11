package com.criticalgnome.payments.userarea;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.Account;
import com.criticalgnome.payments.beans.Card;
import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.dao.AccountDAO;
import com.criticalgnome.payments.dao.CardDAO;
import com.criticalgnome.payments.dao.UserDAO;

/**
 * Servlet implementation class UserArea
 */
@WebServlet("/userarea")
public class UserArea extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(UserArea.class);
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int userID = (int) session.getAttribute("userID");
		User user = null;
		Account account = null;
		List<Card> cards = new ArrayList<Card>();
		try {
			user = UserDAO.getInstance().getUser(userID);
			account = AccountDAO.getInstance().getAccount(userID);
			cards = CardDAO.getInstance().getCards(userID);
		} catch (SQLException e) {
			logger.log(Level.FATAL, "SQL Exception");
		}
		int cardsCount = cards.size();
		request.setAttribute("user", user);
		request.setAttribute("account", account);
		request.setAttribute("cards", cards);
		request.setAttribute("cardsCount", cardsCount);
		request.getRequestDispatcher("userarea.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
