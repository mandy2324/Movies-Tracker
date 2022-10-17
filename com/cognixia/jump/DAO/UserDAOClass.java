package com.cognixia.jump.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnManagerWithProperties;

public class UserDAOClass implements UserDAO {

	private Connection conn = ConnManagerWithProperties.getConnection();

	@Override
	public List<User> getAllUsers() {

		try {
			// find all the departments...
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users");

			List<User> userList = new ArrayList<User>();

			// rs.first();

			while (rs.next()) {
				// ...iterate through to get column info...
				int id = rs.getInt("user_id");
				String name = rs.getString("user_name");
				String phone = rs.getString("user_phone");

				// ...then add them to a list...
				User user = new User(id, name, phone);
				userList.add(user);
			}

			// ...and return that list once finished
			return userList;

		} catch (SQLException e) {
			System.out.println("Could not retrieve list of users from database");
		}

		return null;
	}

	@Override
	public User getUserById(int userid) {
		try {
			// set up prepared statement to get a user using its id
			PreparedStatement pstmt = conn.prepareStatement("select * from users where userId = ?");
			pstmt.setInt(1, userid);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			
			
			// retrieve all column info and save it to user object and return that object
			int id = rs.getInt("userId");
			String name = rs.getString("username");
			String password = rs.getString("password");
				
			User user = new User(id, name, password);
			return user;
			
		} catch (SQLException e) {
			System.out.println("User with id = " + userid + " not found.");
		}
		
		// if department not found, will return null
		return null;
	
	}

	@Override
	public User getUserbyUserName(String username) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from users where username = ?");
			pstmt.setString(1, username);

			ResultSet rs = pstmt.executeQuery();

			rs.next();

			int id = rs.getInt("userId");
			String name = rs.getString("username");
			String password = rs.getString("password");

			User user = new User(id, name, password);
			return user;

		} catch (SQLException e) {
			
			System.out.println("Username = " + username + " not found.");
		}

		return null;
	}

	

}
