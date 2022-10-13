package com.cognixia.jump.DAO;

import java.util.List;

public class TrackerDAOClass implements TrackerDAO{

	@Override
	public List<Movie> getUserMovies(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUserMovie(int user_id, int movie_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserMovie(int user_id, int movie_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userMovieStatus(int user_id, int movie_id, int status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userMovieRating(int user_id, int movie_id, int rating) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
