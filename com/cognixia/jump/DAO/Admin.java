package com.cognixia.jump.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.cognixia.jump.connection.ConnManagerWithProperties;

public class Admin extends User {

	public Admin() {
		
		// TODO Auto-generated constructor stub
	}

	Connection connection = ConnManagerWithProperties.getConnection();

	public void AddMovie() {

		Scanner scan = new Scanner(System.in);

		try {
			String title, summary, genre, length;

			System.out.println("Enter the title of the movie: ");
			title = scan.nextLine();

			System.out.println("Enter the length of the movie in minutes: Ex. HH:MM:SS. Don't forget the ':'");
			length = scan.next();

			System.out.println("Enter the summary of the movie: ");
			scan.nextLine();
			summary = scan.nextLine();

			System.out.println("Enter the genre of the movie: ");
			genre = scan.next();

			String sql = "Insert Into movies(title, length, summary, genre) Values ('" + title + "', '" + length + "', '" + summary + "', '" + genre
					+ "')";
			PreparedStatement p = null;
			p = connection.prepareStatement(sql);
			p.execute();

			System.out.println("Your movie has been added!");

			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void RemoveMovie() {

		Scanner scan = new Scanner(System.in);

		try {
			int idSel = 0;
			PreparedStatement p = null;
			ResultSet rs = null;

			String sql = "select movieId, title from movies";
			p = connection.prepareStatement(sql);
			rs = p.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("movieId");
				String title = rs.getString("title");
				System.out.println(id + "\t\t" + title);

			}

			System.out.println("Enter the ID of the movie you want to remove:");
			idSel = scan.nextInt();
			String sql2 = "Delete From movies Where movieId='" + idSel + "'";
			p.execute(sql2);
			System.out.println("The selected movie has been removed!");

			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void EditMovie() {

		Scanner scan = new Scanner(System.in);

		try {
			int idSel = 0, editSel = 0;
			PreparedStatement p = null;
			ResultSet rs = null;

			String sql = "select movieId, title from movies";
			p = connection.prepareStatement(sql);
			rs = p.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("movieId");
				String title = rs.getString("title");
				System.out.println(id + "\t\t" + title);

			}

			System.out.println("Enter the ID of the movie you want to edit:");
			idSel = scan.nextInt();

			System.out.println("What would you like to edit?\n" + "Press 1 for the title\n" + "Press 2 for the length\n"
					+ "Press 3 for the summary\n" + "Press 4 for the genre");
			editSel = scan.nextInt();

			if (editSel == 1) {

				String newTitle, sql2;

				System.out.println("Enter the new title of the movie: ");
				scan.nextLine();
				newTitle = scan.nextLine();

				sql2 = "Update movies Set title='" + newTitle + "' Where movieId='" + idSel + "'";

				p.execute(sql2);

				System.out.println("The title of movie has been updated!");
			}

			if (editSel == 2) {

				String sql2;

				String newLength;

				System.out.println("Enter the new length of the movie in minutes: Ex. HH:MM:SS. Don't forget the ':'");
				newLength = scan.next();

				sql2 = "Update movies Set length='" + newLength + "' Where movieId='" + idSel + "'";

				p.execute(sql2);

				System.out.println("The length of movie has been updated!");
			}

			if (editSel == 3) {

				String newSumamary, sql2;

				System.out.println("Enter the new summary of the movie: ");
				scan.nextLine();
				newSumamary = scan.nextLine();

				sql2 = "Update movies Set summary='" + newSumamary + "' Where movieId='" + idSel + "'";

				p.execute(sql2);

				System.out.println("The summary of movie has been updated!");
			}

			if (editSel == 4) {

				String newGenre, sql2;

				System.out.println("Enter the new genre of the movie: ");
				newGenre = scan.next();

				sql2 = "Update movies Set genre='" + newGenre + "' Where movieId='" + idSel + "'";

				p.execute(sql2);

				System.out.println("The genre of movie has been updated!");
			}

			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
