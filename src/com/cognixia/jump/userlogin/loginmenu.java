package com.cognixia.jump.userlogin;

import java.sql.SQLException;
import java.util.Scanner;
import com.cognixia.jump.DAO.User;
import com.cognixia.jump.DAO.UserDAO;
import com.cognixia.jump.DAO.UserDAOClass;
import com.cognixia.jump.exception.LoginMenuException;

public class loginmenu {

	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in);
		User test = userPrompt(Scan);
		System.out.println(test);
	}

	@SuppressWarnings("null")
	public static User userPrompt(Scanner scan) {
		String username = null;
		String password = null;

		User user = new User();
		UserDAO userDAO = new UserDAOClass();

		try {
			System.out.print("Please enter username: ");
			username = scan.nextLine();

			System.out.print("Please enter password: ");
			password = scan.nextLine();

			user = userDAO.getUserbyUserName(username);

			if (user == null || user.getPassword() != password) {
				throw new LoginMenuException();
			} else {
				return user;
			}
		} catch (LoginMenuException e) {
			return null;
		} catch (Exception e) {
			System.out.println("Invalid user");
			return null;
		}

	}
}
