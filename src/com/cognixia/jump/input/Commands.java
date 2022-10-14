package com.cognixia.jump.input;

import com.cognixia.jump.DAO.*;
import com.cognixia.jump.userlogin.loginmenu;
import java.util.Scanner;

public class Commands {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Movies-Tracker.");
		System.out.println("Please login to continue");
		String command = "";
		// LOGIN METHOD HERE
		
		
		MovieDAO movieDAO = new MovieDAOClass();
		Movie selected = null;
		
		System.out.println("Welcome, USERNAME. Choose a command or type \"help\" to view commands");
		while (true) {
			System.out.print("Command => ");
			command = scan.nextLine().toLowerCase();
			if (command.equals("help")) {
				// Prints all commands
				System.out.println("Available commands (Case Insensitive):\n"
						+ "list: Prints the list of all movies in the user's list.\n"
						+ "database: Prints all movies available in our database.\n"
						+ "select: Selects a movie. This movie will be the target for future commands.\n"
						+ "show/view: Prints details of selected movie.\n"
						+ "add: Adds selected movie to user's list.\n"
						+ "remove: Removes selected movie from user's list.\n"
						+ "status: Changes watch status of selected movie (Plan to Watch, Watched, On Hold).\n"
						+ "rate: Adds or changes user rating of selected movie (1 to 10).\n"
						+ "logout/exit: Exits the program.");
			}
			/* VIEW DETAILS */
			else if (command.equals("list")) {
				// Shows list of all movies in user list
			}
			else if (command.equals("database")) {
				// Show all movies in database
			}
			
			/* TRACKER */
			else if (command.equals("select")) {
				// Selects movie for future commands
				// Check if movie exists, else throw custom exception
				System.out.print("Which movie? Type the name or id: ");
				String input = scan.nextLine();
				try {
					int ID = Integer.parseInt(input);
					// Input was an int, try searching by ID
					selected = movieDAO.getMovieById(ID);
				} catch (NumberFormatException e) {
					// Input was not an int, try searching by movie title
					selected = movieDAO.getMovieByTitle(input);
				}
				if (selected == null) {
					System.out.println("No movie found by that title. Aborting command...");
				}
				else {
					System.out.println(selected.getTitle() + "selected");
				}
			}
			/* 
			 * BELOW COMMANDS WILL REQUIRE A MOVIE TO BE SELECTED 
			*/
			else if (selected != null) {
				if (command.equals("show") || command.equals("view")) {
					// Prints movie details
					System.out.println(selected);
				}
				else if (command.equals("add")) {
					// Adds movie from database into user's movie list
				}
				else if (command.equals("remove")) {
					// Removes movie from user's movie list
					// Note: Check if movie exists in list first
				}
				else if (command.equals("status")) {
					// Changes user status
					// Note: Convert database int to readable string ("Plan to watch", "Completed")
				}
				else if (command.equals("rate")) {
					// Allows user to add/change rating of movie
				}
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
