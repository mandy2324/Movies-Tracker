package com.cognixia.jump.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnManagerWithProperties;

public class TrackerDAOClass implements TrackerDAO{
	
	private Connection conn = ConnManagerWithProperties.getConnection();

	@Override
	public List<Movie> getUserMovies(int user_id) {
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement("Select movieId, title, genre, length, summary "
					+ "from tracker join movies on movies.movieId = tracker.movieIdF "
					+ "where tracker.userIdF = ?");
			pstmt.setInt(1, user_id);
			
			ResultSet rs = pstmt.executeQuery();
			List<Movie> movieList = new ArrayList<>();
			
			while(rs.next()) {
				int id = rs.getInt("movieId");
				String title = rs.getString("title");
				String genre = rs.getString("genre");
				Time length = rs.getTime("length");
				String summary = rs.getString("summary");
				
				Movie movie = new Movie(id,title,length,summary,genre);
				movieList.add(movie);
			}
			return movieList;
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Could not retrieve list of departments from database");
		}
		
		
		return null;
	}

	@Override
	public boolean addUserMovie(Tracker tracked) {
		if (trackerExists(tracked)) {
			// Already exists, do not add duplicate to list
			System.out.println("Movie already exists in your list.");
			return false;
		}
		try {
		PreparedStatement pstmt = conn.prepareStatement("Insert into tracker(userIdF, movieIdF) values(?,?)");
		pstmt.setInt(1, tracked.getUser_id());
		pstmt.setInt(2, tracked.getMovie_id());
		
		int i = pstmt.executeUpdate();
		
		if(i > 0) {
			return true;
		}
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean removeUserMovie(Tracker tracked) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE from tracker WHERE userIdF = ? and movieIdF = ?");
			pstmt.setInt(1, tracked.getUser_id());
			pstmt.setInt(2, tracked.getMovie_id());
			
			int i = pstmt.executeUpdate();
			
			if(i > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("Movie with id = " + tracked.getMovie_id() + " not found for this user.");
		}
		
		return false;
	}

	@Override
	public boolean userMovieStatus(Tracker tracked, int status) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE tracker SET status = ? WHERE movieIdF = ? and userIdF = ?");
			pstmt.setInt(1, status);
			pstmt.setInt(2, tracked.getMovie_id());
			pstmt.setInt(3, tracked.getUser_id());
			
			int i = pstmt.executeUpdate();
			tracked.setStatus(status);
			
			if(i > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean userMovieRating(Tracker tracked, int rating) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE tracker SET rating = ? WHERE movieIdF = ? and userIdF = ?");
			pstmt.setInt(1, rating);
			pstmt.setInt(2, tracked.getMovie_id());
			pstmt.setInt(3, tracked.getUser_id());
			
			int i = pstmt.executeUpdate();
			tracked.setRating(rating);
			
			if(i > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean trackerExists(Tracker tracked) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("Select * from tracker WHERE userIdF = ? and movieIdF = ?");
			pstmt.setInt(1, tracked.getUser_id());
			pstmt.setInt(2, tracked.getMovie_id());
			
			ResultSet rs = pstmt.executeQuery();
			
			
			boolean check = rs.next();
			if (check) {
				tracked.setRating(rs.getInt("rating"));
				tracked.setStatus(rs.getInt("status"));
			}
			
			
			return check;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Movie with id = " + tracked.getMovie_id() + " not found for this user.");
			return false;
			
		}
		
	}

	
}
