package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.User;

/**
 * @author CriticalGnome
 * Data Access to users table
 */
public class UserDAO {
	
	private static final String INSERT_NEW_RECORD_INTO_USERS = "INSERT INTO users (first_name, last_name, email, password, role) VALUES (?, ?, ?, ?, 'User');";
	private static final String SELECT_FROM_USERS_WITH_ID = "SELECT * FROM users WHERE id = ?;";
	private static final String SELECT_FROM_USERS_WITH_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?;";
	private static final String UPDATE_FIRST_AND_LAST_NAMES = "UPDATE users SET first_name = ?, last_name = ? WHERE id = ?;";
	private static final String GET_USER_ID_BY_EMAIL = "SELECT id FROM users WHERE email = ?;";
	
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
		con = ConnectionPool.getInstance().getConnection();
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
		ConnectionPool.getInstance().releaseConnection(con);
		return user;
	}

	/**
	 * Get user ID by email
	 * @param email
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public int getId(String email) throws SQLException, IOException {
		int id = 0;
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(GET_USER_ID_BY_EMAIL);
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		if (rs.next()) {
			id = rs.getInt("id");
		}
		ConnectionPool.getInstance().releaseConnection(con);
		return id;
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
		con = ConnectionPool.getInstance().getConnection();
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
		ConnectionPool.getInstance().releaseConnection(con);
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
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(INSERT_NEW_RECORD_INTO_USERS);
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setString(3, email);
		stmt.setString(4, password);
		stmt.executeUpdate();
		logger.log(Level.INFO, "Add new User: {} {}, {}", firstName, lastName, email);
		ConnectionPool.getInstance().releaseConnection(con);
	}

	/**
	 * Update First and Last Names by User ID
	 * @param firstName
	 * @param lastName
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateName(String firstName, String lastName, int id) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(UPDATE_FIRST_AND_LAST_NAMES);
		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setInt(3, id);
		stmt.executeUpdate();
		logger.log(Level.INFO, "Update User First and Last Names [id={}]: {} {}", id, firstName, lastName);
		ConnectionPool.getInstance().releaseConnection(con);
	}

}
