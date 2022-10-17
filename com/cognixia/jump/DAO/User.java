package com.cognixia.jump.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jump.connection.ConnManagerWithProperties;

public class User {
	
	//Creating variables for User to operate
	private int id;
	private String username;
	private String password;
	
	//Constructor to initialize new Department Objects
	public User() {
		this.id=0;
		this.username="";
		this.password="";
	}
	

	Connection connection = ConnManagerWithProperties.getConnection();


	//Constructor to initialize new Department Objects
	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	
	//Getter and Setters to manipulate data directly
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}


	public void addAccount(String username2, String password2, String email2, String phoneNumber2) {

		try {
			PreparedStatement pstmt = connection.prepareStatement("Insert Into users(username, password, email, phoneNumber) " + "Values(?, ?, ?, ?)");
			pstmt.setString(1, username2);
			pstmt.setString(2, password2);
			pstmt.setString(3, email2);
			pstmt.setString(4, phoneNumber2);
            pstmt.execute();
			
			System.out.println("You have been added");
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
