package com.cognixia.jump.userlogin;

import java.util.Scanner;

import com.cognixia.jump.DAO.User;
import com.cognixia.jump.DAO.UserDAO;
import com.cognixia.jump.DAO.UserDAOClass;
import com.cognixia.jump.exception.LoginMenuException;

public class UserLogin {
	public User userPrompt(Scanner scan) {
		
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
			
			if (user == null || !user.getPassword().equals(password)) {
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
