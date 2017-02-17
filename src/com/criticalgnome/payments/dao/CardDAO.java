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

import com.criticalgnome.payments.beans.Card;

/**
 * @author CriticalGnome
 * Data Access to card table
 */
public class CardDAO {
	
	private static final String SELECT_FROM_CREDIT_CARD_WITH_USER_ID = "SELECT credit_card.id, credit_card.number AS number, credit_card.expiration_month AS exp_month, credit_card.expiration_year AS exp_year, credit_card.comment from account, credit_card WHERE account.client_id = ? AND account.id = credit_card.account_id;";
	private static final String ADD_NEW_CARD = "INSERT INTO credit_card (number, expiration_month, expiration_year, account_id) VALUES (?, ?, ?, ?);";
	private static volatile CardDAO instance;
	private static final Logger logger = LogManager.getLogger(CardDAO.class);
	
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
	 * Get all User's cards by ID
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public List<Card> getCards(int id) throws SQLException, IOException {
		List<Card> cards = new ArrayList<Card>();
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(SELECT_FROM_CREDIT_CARD_WITH_USER_ID);
		stmt.setInt(1, id);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Card card = new Card.Builder()
					.id(rs.getInt("id"))
					.number(rs.getString("number"))
					.expMonth(rs.getString("exp_month"))
					.expYear(rs.getString("exp_year"))
					.comment(rs.getString("comment"))
					.build();
			cards.add(card);
		}
		con.close();
		stmt.close();
		rs.close();
		return cards;
	}
	
	/**
	 * Create new Card record
	 * @param card
	 * @param accountId
	 * @throws SQLException
	 * @throws IOException
	 */
	public void createCard(Card card, int accountId) throws SQLException, IOException {
		con = ConnectionPool.getInstance().getConnection();
		stmt = con.prepareStatement(ADD_NEW_CARD);
		stmt.setString(1, card.getNumber());
		stmt.setString(2, card.getExpMonth());
		stmt.setString(3, card.getExpYear());
		stmt.setInt(4, accountId);
		stmt.executeUpdate();
		con.close();
		stmt.close();
		logger.log(Level.INFO, "Card [{}] created for Account id={}", card.getNumber(), accountId);
	}

}
