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

import com.criticalgnome.payments.beans.Payment;

public class PaymentDAO {
	
	private static final String PAYMENT_FROM_ACCOUNT = "UPDATE account SET amount = amount - ? WHERE id = ?;";
	private static final String PAYMENT_TO_ACCOUNT = "UPDATE account SET amount = amount + ? WHERE id = ?;";
	private static final String PAYMENT_SAVE_IN_TABLE = "INSERT INTO payment (amount, comment, account_id, destination_id) VALUES (?,?,?,?);";
	private static final String GET_LAST_PAYMENTS = "SELECT payment.date_time, account.number, users.first_name, users.last_name, payment.amount, payment.comment, payment.account_id FROM payment, account, users WHERE (payment.account_id = ? AND payment.destination_id = account.id AND account.client_id = users.id) OR (payment.destination_id = ? AND payment.account_id = account.id AND account.client_id = users.id) LIMIT 100;";

	private static volatile PaymentDAO instance;
	private static final Logger logger = LogManager.getLogger(PaymentDAO.class);

	private PaymentDAO(){
		
	}
	
	/**
	 * Singleton
	 */
	public static PaymentDAO getInstance() {
		if (instance == null) {
			synchronized (PaymentDAO.class) {
				if (instance == null) {
					instance = new PaymentDAO();
				}
			}
		}
		return instance;
	}

	private static Connection con;
	private static PreparedStatement stmt;
	private static ResultSet rs;
	
	/**
	 * Make From-To-Accounts Payment
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 * @param comment
	 * @throws SQLException
	 * @throws IOException
	 */
	public void makePayment(int fromAccount, int toAccount, int amount, String comment) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(PAYMENT_FROM_ACCOUNT);
		stmt.setInt(1, amount);
		stmt.setInt(2, fromAccount);
		stmt.executeUpdate();
		stmt = con.prepareStatement(PAYMENT_TO_ACCOUNT);
		stmt.setInt(1, amount);
		stmt.setInt(2, toAccount);
		stmt.executeUpdate();
		stmt = con.prepareStatement(PAYMENT_SAVE_IN_TABLE);
		stmt.setInt(1, amount);
		stmt.setString(2, comment);
		stmt.setInt(3, fromAccount);
		stmt.setInt(4, toAccount);
		stmt.executeQuery();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "Payment from {} to {} amount {} ({})", fromAccount, toAccount, amount, comment);
	}
	
	public List<Payment> getPayments (int id) throws SQLException, IOException {
		List<Payment> payments = new ArrayList<Payment>();
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(GET_LAST_PAYMENTS);
		stmt.setInt(1, id);
		stmt.setInt(2, id);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Payment payment = new Payment.Builder()
					.dateTime(rs.getString("date_time"))
					.number(rs.getInt("number"))
					.firstName(rs.getString("first_name"))
					.lastName(rs.getString("last_name"))
					.amount(rs.getInt("amount"))
					.comment(rs.getString("comment"))
					.accountID(rs.getInt("account_id"))
					.build();
			payments.add(payment);
		}
		con.close();
		stmt.close();
		rs.close();
		return payments;
	}
	
}
