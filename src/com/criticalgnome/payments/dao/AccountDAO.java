package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.criticalgnome.payments.beans.Account;
import com.criticalgnome.payments.beans.AvailableForPayment;
import com.criticalgnome.payments.beans.BlockedAccount;

/**
 * @author CriticalGnome
 * Data Access to account table
 */
public class AccountDAO {
	
	private static final String SELECT_FROM_ACCOUNT_WITH_USER_ID = "SELECT * FROM account WHERE client_id = ?;";
	private static final String UPDATE_ACCOUNT_WITH_ID_AND_AMOUNT = "UPDATE account SET amount = ? WHERE client_id = ?;";
	private static final String BLOCK_ACCOUNT_WITH_USER_ID = "UPDATE account SET is_blocked = 1 WHERE client_id = ?;";
	private static final String SELECT_BLOCKED_ACCOUNT = "SELECT account.id, account.number, account.amount, users.first_name, users.last_name, users.role FROM account, users WHERE is_blocked = 1 AND account.client_id = users.id;";
	private static final String UNBLOCK_ACCOUNT_WITH_ID = "UPDATE account SET is_blocked = 0 WHERE id = ?;";
	private static final String LIST_OF_AVAILABLE_ACCOUNTS = "SELECT account.id, account.number, users.first_name, users.last_name FROM account, users WHERE account.client_id = users.id;";
	private static final String GET_MAX_ACCOUNT_NUMBER = "SELECT max(number) FROM account;";
	private static final String CREATE_ACCOUNT_FOR_NEW_USER = "INSERT INTO account (number, amount, is_blocked, client_id) VALUES (?, 0, 0, ?);";
	
	private static volatile AccountDAO instance;
	private static final Logger logger = LogManager.getLogger(AccountDAO.class);

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

	/**
	 * Update Amount by User ID 
	 * @param id
	 * @param amount
	 * @throws SQLException
	 * @throws IOException
	 */
	public void updateAmount(int id, int amount) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(UPDATE_ACCOUNT_WITH_ID_AND_AMOUNT);
		stmt.setInt(1, amount);
		stmt.setInt(2, id);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "User [id={}] has new amount: {}", id, amount);
	}

	/**
	 * Block Account by User ID
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 */
	public void blockAccount(int id) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(BLOCK_ACCOUNT_WITH_USER_ID);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "User [id={}] block own account", id);
	}
	
	/**
	 * UnBlock Account by Account ID
	 * @param id
	 * @throws SQLException
	 * @throws IOException
	 */
	public void unBlockAccount(int id) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(UNBLOCK_ACCOUNT_WITH_ID);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "Administrator unblock account {}", id);
	}
	
	/**
	 * Create List of blocked Accounts
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<BlockedAccount> getBlockedAccounts() throws SQLException, IOException {
		List<BlockedAccount> blockedAccounts = new ArrayList<BlockedAccount>();
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_BLOCKED_ACCOUNT);
		rs = stmt.executeQuery();
		while (rs.next()) {
			BlockedAccount account = new BlockedAccount.Builder()
					.id(rs.getInt("id"))
					.number(rs.getInt("number"))
					.amount(rs.getLong("amount"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.role(rs.getString("role"))
					.build();
			blockedAccounts.add(account);
		}
		con.close();
		stmt.close();
		rs.close();
		return blockedAccounts;
	}
	
	/**
	 * Create List of available Accounts
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<AvailableForPayment> getAllAvailableAccounts() throws SQLException, IOException {
		List<AvailableForPayment> availableAccounts = new ArrayList<AvailableForPayment>();
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(LIST_OF_AVAILABLE_ACCOUNTS);
		rs = stmt.executeQuery();
		while (rs.next()) {
			AvailableForPayment account = new AvailableForPayment.Builder()
					.id(rs.getInt("id"))
					.number(rs.getInt("number"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.build();
			availableAccounts.add(account);
		}
		con.close();
		stmt.close();
		rs.close();
		return availableAccounts;
	}
	
	/**
	 * Get last account number
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public int getmaxAccountNumber() throws SQLException, IOException {
		int maxNumber = 0;
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(GET_MAX_ACCOUNT_NUMBER);
		rs = stmt.executeQuery();
		if (rs.next()) {
			maxNumber = rs.getInt("max(number)");
		}
		con.close();
		stmt.close();
		rs.close();
		return maxNumber;
	}
	
	/**
	 * Create new Account in Database
	 * @param number
	 * @param userID
	 * @throws SQLException
	 * @throws IOException
	 */
	public void createNewAccount(int number, int userID) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(CREATE_ACCOUNT_FOR_NEW_USER);
		stmt.setInt(1, number);
		stmt.setInt(2, userID);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "Create account ({}) fo new user [id={}]", number, userID);
	}
	
}
