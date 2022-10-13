package com.cognixia.jump.userlogin;

import java.sql.SQLException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import com.cognixia.jump.DAO.User;

public class loginmenu {
	public static int id = 0;

	public static void main(String[] args) {

	}

	public static void userPrompt(Scanner scan)throws SQLException {
		System.out.println("Movies Tracker");	
		
		while(id == 0) {
			
			String username = null, password = null;
			User user = new User();
			
			try {
				System.out.println("Please enter username or Enter q to quit the application");
				username = scan.next();
				
				if ((username.equals("q"))) {
					System.out.println("Quit");
					return;
				}
				System.out.println("Please enter password or Enter q to quit the application");
			
				if ((password.equals("q"))) {
					System.out.println("Good Bye!!!");
					return;
				}
				
				user.setUsername(username);
				user.setPassword(password);
				
				user = new User();
//				if user has been grabbed from database
				if (id == 0) {
					throw new LoginException();
				}
			}catch (LoginException e ) {
					
			}catch (Exception e ) {
				System.out.println("Invalid input, Try again");
			}
			}
			}
		}
