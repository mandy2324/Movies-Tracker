package com.cognixia.jump.input;

import com.cognixia.jump.DAO.*;
import com.cognixia.jump.userlogin.loginmenu;
import java.util.Scanner;

public class Commands {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to Movies-Tracker.");
		System.out.println("Please login to continue");
		String command = "";
		// LOGIN METHOD HERE
		User user = loginmenu.userPrompt(scan);

		MovieDAO movieDAO = new MovieDAOClass();
		TrackerDAO trackerDAO = new TrackerDAOClass();
		Movie selected = null;
		Tracker tracker = null;

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
			} else if (command.equals("database")) {
				// Show all movies in database
				for (Movie movie : movieDAO.getAllMovies()) {
					System.out.println(movie);
				}
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
					continue;
				} else {
					System.out.println(selected.getTitle() + "selected");
					// Create Tracker object between user and selected movie
					tracker = new Tracker(selected.getId(), user.getId());
				}
			}
			/*
			 * BELOW COMMANDS WILL REQUIRE A MOVIE TO BE SELECTED
			 */
			else if (selected != null) {
				if (command.equals("show") || command.equals("view")) {
					// Prints movie details
					System.out.println(selected);
				} else if (command.equals("add")) {
					// Adds movie from database into user's movie list
					// Add tracker to database
				}
				// if trackerDAO method to check if tracker exists in database
				if (trackerDAO.trackerExists(tracker)) {
					if (command.equals("remove")) {
						// Removes movie from user's movie list
						// Note: Check if movie exists in list first
						trackerDAO.removeUserMovie(tracker);
					} else if (command.equals("status")) {
						// Changes user status
						// Note: Convert database int to readable string ("Plan to watch", "Completed")
						System.out.println(tracker.getStatus());
					} else if (command.equals("changestatus")) {
						// 1: Plan to Watch
						// 2: Watched
						// 3: On Hold
						System.out.print(
								"Choose status (Plan to Watch, Watched, or On Hold) for " + selected.getTitle() + ": ");
						String newStatus = scan.nextLine();

						if (newStatus.equals("1") || newStatus.equalsIgnoreCase("plan to watch")
								|| newStatus.equalsIgnoreCase("ptw")) {
							trackerDAO.userMovieStatus(tracker, 1);
						} else if (newStatus.equals("2") || newStatus.equalsIgnoreCase("watched")
								|| newStatus.equalsIgnoreCase("w")) {
							trackerDAO.userMovieStatus(tracker, 2);
						} else if (newStatus.equals("3") || newStatus.equalsIgnoreCase("on hold")
								|| newStatus.equalsIgnoreCase("h")) {
							trackerDAO.userMovieStatus(tracker, 3);
						} else {
							System.out.println("Not a valid status. Aborting command...");
							continue;
						}
					} else if (command.equals("rate")) {
						// Allows user to add/change rating of movie
						System.out.print("Choose rating for " + selected.getTitle() + ": ");
						int newRating = scan.nextInt();
						trackerDAO.userMovieRating(tracker, newRating);
					}
				}

			}

			else if (command == "logout" || command == "exit") {
				// Logout user, end program
				scan.close();
				return;
			} else {
				System.out.println("Command not found");
			}
		}
	}
}
