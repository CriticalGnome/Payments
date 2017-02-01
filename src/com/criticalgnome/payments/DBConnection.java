package com.criticalgnome.payments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	// JDBC URL, username and password of MySQL server
	private static final String url = "jdbc:mysql://localhost:3306/payments?autoReconnect=true&useSSL=false";
	private static final String user = "payments";
	private static final String password = "payments";

	// JDBC variables for opening and managing connection
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs;

	public static String getPasswordFromDB(String email) {
		String dbPassword = null;
		String query = "SELECT users.password FROM emails, users WHERE emails.address = \"" + email
				+ "\" AND emails.client_id = users.id;";
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				dbPassword = rs.getString("password");
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			// close connection ,stmt and resultset here
			try {
				con.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				stmt.close();
			} catch (SQLException se) {
				/* can't do anything */ }
			try {
				rs.close();
			} catch (SQLException se) {
				/* can't do anything */ }
		}
		return dbPassword;
	}
}
