package com.criticalgnome.payments.actions;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.dao.UserDAO;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(login.class);
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = UserDAO.getInstance().getUser(request.getParameter("email"), request.getParameter("password"));
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("isAuthorized", "yes");
				session.setAttribute("userID", user.getId());
				session.setAttribute("role", user.getRole());
				logger.log(Level.INFO, "User {} [id={}] get login by role {})", user.getEmail(), user.getId(), user.getRole());
				response.sendRedirect("index.jsp?action=login");
			} else {
				logger.log(Level.WARN, "Incorrect user data (email=\"{}\", password=\"{}\")", request.getParameter("email"), request.getParameter("password"));
				response.sendRedirect("login.jsp?action=wronglogin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
