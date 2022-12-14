package com.cognixia.jump.userlogin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.cognixia.jump.DAO.Admin;
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
		String adminSel = null;
		String sel = null;

		User user = new User();
		UserDAO userDAO = new UserDAOClass();

		try {
			System.out.println("If you would like to continue as admin enter a. If not enter anything else:");
			adminSel = scan.nextLine();
			
			if (adminSel.equals("a")) {
				String adminPass, adminPassFile;
				int selection;

				Path path = Paths.get("resources/adminPass.txt");
				
				System.out.println("Enter the password to continue as admin:");
				adminPass = scan.nextLine();
				
				adminPassFile = Files.readString(path);
				
			    if(adminPass.equals(adminPassFile)) {
					System.out.println("Enter 1 if you would like to add a movie.\n" +
					"Enter 2 if you would like to remove a movie.\n" + "Enter 3 if you would like to edit a movie.\n");
					selection = scan.nextInt();
					scan.nextLine();
					
					if(selection == 1) {
						new Admin().AddMovie();
					}
                    if(selection == 2) {
                    	new Admin().RemoveMovie();
					}
                    if(selection == 3) {
                    	new Admin().EditMovie();
					}
				}
				
			}
			
			System.out.println("If you would like to create a new account enter c. If not enter anything else:");
			sel = scan.nextLine();
			
			if (sel.equals("c")) {
				String newUsername, newPassword, email, phoneNumber;
				
				System.out.println("Enter your username for your new account");
				newUsername = scan.nextLine();
				
				System.out.println("Enter your password for your new account");
				newPassword = scan.nextLine();
				
				System.out.println("Enter your email for your new account");
				email = scan.nextLine();
				
				System.out.println("Enter your phone number for your new account");
				phoneNumber = scan.nextLine();
				
				user.addAccount(newUsername, newPassword, email, phoneNumber);
			}

			
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
