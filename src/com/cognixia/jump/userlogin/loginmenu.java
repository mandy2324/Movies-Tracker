package com.cognixia.jump.userlogin;

import java.sql.SQLException;
import java.util.Scanner;
import com.cognixia.jump.DAO.User;
import com.cognixia.jump.exception.LoginMenuException;

public class loginmenu {

	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in);
		try {
			userPrompt(Scan);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static void userPrompt(Scanner scan) throws SQLException {
		System.out.println("Welcome to Movies Tracker");
		int id = 0;
		while (id == 0) {

			String username = null;
			String password = null;

			User user = new User();

			try {
				System.out.println("Please enter username or Enter q to quit the application");
				username = scan.nextLine();

				if ((username.equals("q"))) {
					System.out.println("Quit");
					return;
				}
				System.out.println("Please enter password or Enter q to quit the application");
				password = scan.nextLine();

				if ((password.equals("q"))) {
					System.out.println("Good Bye!!!");
					return;
				}

				user.setUsername(username);
				user.setPassword(password);

				if (id == 0) {
					throw new LoginMenuException();

				}
			} catch (LoginMenuException e) {

			} catch (Exception e) {
				System.out.println("Invalid user");
			}

		}

	}
}
