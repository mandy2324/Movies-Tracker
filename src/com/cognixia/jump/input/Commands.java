package com.cognixia.jump.input;

import com.cognixia.jump.DAO.*;
import com.cognixia.jump.exception.CommandNotFoundException;

import com.cognixia.jump.userlogin.loginmenu;

import java.util.List;
import java.util.Scanner;

public class Commands {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("*---------------------------------------*\n" + "|       Welcome to Movies-Tracker       |\n"
				+ "*---------------------------------------*");
		System.out.println("Please login to continue");
		String command = "";
		// LOGIN METHOD HERE
		User user = loginmenu.userPrompt(scan);

		MovieDAO movieDAO = new MovieDAOClass();
		TrackerDAO trackerDAO = new TrackerDAOClass();
		Movie selected = null;
		Tracker tracker = null;

		System.out.println("Welcome, " + user.getUsername() + ". Choose a command or type \"help\" to view commands");
		while (true) {
			try {
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
							+ "status: Prints watch status of movie.\n"
							+ "changestatus: Changes watch status of selected movie (Plan to Watch, Watched, On Hold).\n"
							+ "rating: Prints user rating of selected movie.\n"
							+ "rate: Adds or changes user rating of selected movie (1 to 10).\n"
							+ "logout/exit: Exits the program.");
				} else if (command.equals("logout") || command.equals("exit")) {
					// Logout user, end program
					System.out.println("Exiting program.");
					scan.close();
					System.exit(0);
				}
				/* VIEW DETAILS */
				else if (command.equals("list")) {
					// Shows list of all movies in user list
					List<Movie> trackerlist = trackerDAO.getUserMovies(user.getId());
					System.out.println(user.getUsername() + "'s List:");
					for (int i = 0; i < trackerlist.size(); i++) {
						if (i != 0) {
							System.out.println("*----------------------------*");
						}
						System.out.println(trackerlist.get(i));
						// prints tracker status and rating if it exists
						Tracker temp = new Tracker(trackerlist.get(i).getId(), user.getId());
						if (trackerDAO.trackerExists(temp)) {
							// Print status and rating as well
							System.out.println("Watch status: " + temp.getStatus());
							System.out.println("User rating: " + temp.getRating());
						}
					}
					System.out.println();
				} else if (command.equals("database")) {
					// Show all movies in database
					List<Movie> movieList = movieDAO.getAllMovies();
					for (int i = 0; i < movieList.size(); i++) {
						if (i != 0) {
							System.out.println("*----------------------------*");
						}
						System.out.println(movieList.get(i));
						// prints tracker status and rating if it exists
						Tracker temp = new Tracker(movieList.get(i).getId(), user.getId());
						if (trackerDAO.trackerExists(temp)) {
							// Print status and rating as well
							System.out.println("Watch status: " + temp.getStatus());
							System.out.println("User rating: " + temp.getRating());
						}
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
						System.out.println(selected.getTitle() + " selected");
						// Create Tracker object between user and selected movie
						tracker = new Tracker(selected.getId(), user.getId());
						trackerDAO.trackerExists(tracker); // Updates tracker to get rating and status if already applied
					}
				}
				/*
				 * BELOW COMMANDS WILL REQUIRE A MOVIE TO BE SELECTED
				 */
				else if (selected != null) {
					if (command.equals("show") || command.equals("view")) {
						// Prints movie details
						System.out.println(selected);
						if (trackerDAO.trackerExists(tracker)) {
							// Print status and rating as well
							System.out.println("Watch status: " + tracker.getStatus());
							System.out.println("User rating: " + tracker.getRating());
						}
					} else if (command.equals("add")) {
						// Adds movie from database into user's movie list
						// Add tracker to database
						if (trackerDAO.addUserMovie(tracker)) {
							System.out.println(selected.getTitle() + " successfully added to user list");
						}
						else {
							System.out.println("Failed to add " + selected.getTitle() + " to user list");
						}
					}
					// if trackerDAO method to check if tracker exists in database
					else if (trackerDAO.trackerExists(tracker)) {
						if (command.equals("remove")) {
							// Removes movie from user's movie list
							// Note: Check if movie exists in list first
							trackerDAO.removeUserMovie(tracker);
							System.out.println(selected.getTitle() + " successfully removed from user list");
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
						} else if (command.equals("viewrating") || command.equals("rating")) {
							System.out.println("Rating for " + selected.getTitle() + ": " + tracker.getRating());
						} else if (command.equals("rate")) {
							// Allows user to add/change rating of movie
							System.out.print("Choose rating for " + selected.getTitle() + ": ");
							int newRating = scan.nextInt();
							trackerDAO.userMovieRating(tracker, newRating);
							System.out.println(selected.getTitle() + " rated as " + newRating);
							scan.nextLine(); // Buffers out the newline after reading int
						} else {
							throw new CommandNotFoundException();
						}
					} else {
						throw new CommandNotFoundException();
					}
	
				} else {
					throw new CommandNotFoundException();
				}} catch (CommandNotFoundException e) {
					
				}
		}
	}
}
