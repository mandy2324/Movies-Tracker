package com.cognixia.jump.DAO;

import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnManagerWithProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class MovieDAOClass implements MovieDAO{
	// save connection as attribute so access is easier
	private Connection conn = ConnManagerWithProperties.getConnection();
	
	@Override
	public List<Movie> getAllMovies() {
		try {
			// find all the departments...
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM department");
			
			List<Movie> movieList = new ArrayList<Movie>();
			
			//rs.first();
			
			while(rs.next()) {
				// ...iterate through to get column info...
				int id = rs.getInt("movieId");
				String title = rs.getString("title");
				Time length = rs.getTime("length");
				String summary = rs.getString("summary");
				String genre = rs.getString("genre");
				
				// ...then add them to a list...
				Movie movie = new Movie(id, title, length, summary, genre);
				movieList.add(movie);
			}
			
			// ...and return that list once finished
			return movieList;
			
		} catch (SQLException e) {
			System.out.println("Could not retrieve list of departments from database");
		}
		
		// return null just in case exception is thrown
		return null;
	}

	@Override
	public Movie getMovieById(int movieId) {
		try {
			// set up prepared statement to get a department using its id
			PreparedStatement pstmt = conn.prepareStatement("select * from department where movieId = ?");
			pstmt.setInt(1, movieId);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.first();
			
			
			// retrieve all column info and save it to Department object and return that object
			int id = rs.getInt("movieId");
			String title = rs.getString("title");
			Time length = rs.getTime("length");
			String summary = rs.getString("summary");
			String genre = rs.getString("genre");
				
			Movie movie = new Movie(id, title, length, summary, genre);
			return movie;
			
		} catch (SQLException e) {
			System.out.println("Department with id = " + movieId + " not found.");
		}
		
		// if department not found, will return null
		return null;
	}

	@Override
	public Movie getMovieByTitle(String title_) {
		try {
			// set up prepared statement to get a department using its id
			PreparedStatement pstmt = conn.prepareStatement("select * from department where title = ?");
			pstmt.setString(1, title_);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.first();
			
			
			// retrieve all column info and save it to Department object and return that object
			int id = rs.getInt("movieId");
			String title = rs.getString("title");
			Time length = rs.getTime("length");
			String summary = rs.getString("summary");
			String genre = rs.getString("genre");
				
			Movie movie = new Movie(id, title, length, summary, genre);
			return movie;
			
		} catch (SQLException e) {
			System.out.println("Department with id = " + title_ + " not found.");
		}
		
		// if department not found, will return null
		return null;
	}

}
