package com.cognixia.jump.DAO;

public class Tracker {
	//Variables
	private int movie_id;
	private int user_id;
	private int status;
	private int rating;
	
	//Constructor
	public Tracker(int movie_id, int user_id) {
		super();
		this.movie_id = movie_id;
		this.user_id = user_id;
		
	}

	//Getters and Setters
	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Tracker [movie_id=" + movie_id + ", user_id=" + user_id + ", status=" + status + ", rating=" + rating
				+ "]";
	}
	
	
	
	
}
