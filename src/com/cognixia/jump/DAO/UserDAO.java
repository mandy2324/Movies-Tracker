package com.cognixia.jump.DAO;

import java.util.List;

public interface UserDAO {

	public List<User> getAllUsers();
	public User getUserById(int id);
	public User getUserbyUserName(String username);
	
}
