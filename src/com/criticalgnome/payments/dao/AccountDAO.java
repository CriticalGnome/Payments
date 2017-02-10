package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.criticalgnome.payments.beans.Account;

/**
 * @author CriticalGnome
 * Data Access to users table
 */
public class AccountDAO {
	
	private static final String SELECT_FROM_ACCOUNT_WITH_USER_ID = "SELECT * FROM account WHERE client_id = ?;";
	private static volatile AccountDAO instance;
	
	private AccountDAO(){
		
	}
	
	/**
	 * Singleton
	 */
	public static AccountDAO getInstance() {
		if (instance == null) {
			synchronized (AccountDAO.class) {
				if (instance == null) {
					instance = new AccountDAO();
				}
			}
		}
		return instance;
	}
	
	private static Connection con;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
	/**
	 * Get account by user id
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public Account getAccount(int id) throws SQLException, IOException {
		Account account = null;
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_FROM_ACCOUNT_WITH_USER_ID);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		if (rs.next()) {
			account = new Account.Builder()
					.id(rs.getInt("id"))
					.number(rs.getInt("number"))
					.amount(rs.getLong("amount"))
					.isBlocked(rs.getBoolean("is_blocked"))
					.comment(rs.getString("comment"))
					.build();
		}
		con.close();
		stmt.close();
		rs.close();
		return account;
	}

}
