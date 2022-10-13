package com.cognixia.jump.input;

import java.util.Scanner;

public class Commands {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Movies-Tracker.");
		System.out.println("Please login to continue");
		String command = "";
		// LOGIN METHOD HERE
		
		System.out.println("Welcome, USERNAME. Choose a command or type \"help\" to view commands");
		while (true) {
			command = scan.nextLine().toLowerCase();
			if (command == "help") {
				// Prints all commands
			}
			/* VIEW DETAILS */
			else if (command == "list") {
				// Shows list of all movies in user list
			}
			else if (command == "database") {
				// Show all movies in database
			}
			
			/* TRACKER */
			else if (command == "select") {
				// Selects movie for future commands
				// Check if movie exists, else throw custom exception
			}
			/* 
			 * BELOW COMMANDS WILL REQUIRE A MOVIE TO BE SELECTED 
			*/
			else if (command == "show" || command == "view") {
				// Prints movie details
			}
			else if (command == "add") {
				// Adds movie from database into user's movie list
			}
			else if (command == "remove") {
				// Removes movie from user's movie list
				// Note: Check if movie exists in list first
			}
			else if (command == "status") {
				// Changes user status
				// Note: Convert database int to readable string ("Plan to watch", "Completed")
			}
			else if (command == "rate") {
				// Allows user to add/change rating of movie
			}
			
			
			else if (command == "logout" || command == "exit") {
				// Logout user, end program
				scan.close();
				return;
			}
			else {
				System.out.println("Command not found");
			}
		}
	}
}
