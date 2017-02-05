package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.utils.ConfigParser;

/**
 * @author CriticalGnome
 * Data Access to users table
 */
public class UserDAO {
	
	private static final String INSERT_NEW_RECORD_INTO_USERS = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, 'User');";
	private static final String SELECT_FROM_USERS_WITH_ID = "SELECT * FROM users WHERE id = ?;";
	private static final String SELECT_FROM_USERS_WITH_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?;";
	private static volatile UserDAO instance;
	private static final Logger logger = LogManager.getLogger(UserDAO.class);
	
	private UserDAO(){
		
	}
	
	/**
	 * Singleton
	 */
	public static UserDAO getInstance() {
		if (instance == null) {
			synchronized (UserDAO.class) {
				if (instance == null) {
					instance = new UserDAO();
				}
			}
		}
		return instance;
	}
	
	private static Connection con;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
	/**
	 * Get user by id
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public User getUser(int id) throws SQLException, IOException {
		User user = null;
		con = ConnectionManager.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_FROM_USERS_WITH_ID);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			user = new User.Builder()
					.id(rs.getInt("id"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.email(rs.getString("email"))
					.password(rs.getString("password"))
					.comment(rs.getString("comment"))
					.role(rs.getString("role"))
					.build();
		}
		con.close();
		stmt.close();
		rs.close();
		return user;
	}

	/**
	 * Get user by email and password
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public User getUser(String email, String password) throws SQLException, IOException {
		User user = null;
		con = ConnectionManager.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_FROM_USERS_WITH_EMAIL_AND_PASSWORD);
		stmt.setString(1, email);
		stmt.setString(2, password);
		rs = stmt.executeQuery();
		if (rs.next()) {
			user = new User.Builder()
					.id(rs.getInt("id"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.email(rs.getString("email"))
					.password(rs.getString("password"))
					.comment(rs.getString("comment"))
					.role(rs.getString("role"))
					.build();
		}
		con.close();
		stmt.close();
		rs.close();
		return user;
	}

	/**
	 * Add new User into Database
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addUser(String firstName, String lastName, String email, String password) throws SQLException, IOException {
		con = ConnectionManager.getInstance().getConnection();
		stmt = con.prepareStatement(INSERT_NEW_RECORD_INTO_USERS);
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setString(3, email);
		stmt.setString(4, password);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "Add new User: {} {}, {}", firstName, lastName, email);
	}

}
