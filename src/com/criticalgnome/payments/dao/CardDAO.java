package com.criticalgnome.payments.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.criticalgnome.payments.beans.Card;

/**
 * @author CriticalGnome
 * Data Access to users table
 */
public class CardDAO {
	
	private static final String SELECT_FROM_CREDIT_CARD_WITH_USER_ID = "SELECT credit_card.id, credit_card.number AS number, credit_card.expiration_month AS exp_month, credit_card.expiration_year AS exp_year, credit_card.comment from account, credit_card WHERE account.client_id = ? AND account.id = credit_card.account_id;";
	private static volatile CardDAO instance;
	
	private CardDAO(){
		
	}
	
	/**
	 * Singleton
	 */
	public static CardDAO getInstance() {
		if (instance == null) {
			synchronized (CardDAO.class) {
				if (instance == null) {
					instance = new CardDAO();
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
	public List<Card> getAccount(int id) throws SQLException, IOException {
		List<Card> cards = new ArrayList<Card>();
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_FROM_CREDIT_CARD_WITH_USER_ID);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Card card = new Card.Builder()
					.id(rs.getInt("id"))
					.number(rs.getLong("number"))
					.expMonth(rs.getInt("exp_month"))
					.expYear(rs.getInt("exp_year"))
					.comment(rs.getString("comment"))
					.build();
			cards.add(card);
		}
		con.close();
		stmt.close();
		rs.close();
		return cards;
	}

}
