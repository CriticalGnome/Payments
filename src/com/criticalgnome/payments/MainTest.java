package com.criticalgnome.payments;

import java.io.IOException;
import java.sql.SQLException;

import com.criticalgnome.payments.beans.User;
import com.criticalgnome.payments.dao.UserDAO;

/**
 * @author CriticalGnome
 *
 */
public class MainTest {

	/**
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException, IOException {
		User user = null;
		user = UserDAO.getInstance().getUser(1);
		System.out.println("By ID: " + user);
		user = UserDAO.getInstance().getUser("lord.skiminok@gmail.com", "qwerty");
		System.out.println("By login + pass: " + user);
		user = UserDAO.getInstance().getUser("lord.skiminok@gmail.com", "123");
		System.out.println("By login + wrong pass: " + user);
		user = UserDAO.getInstance().getUser("lord@gmail.com", "qwerty");
		System.out.println("By wrong login + wrong pass: " + user);
	}

}
