package com.cognixia.jump.DAO;

import java.util.List;

public interface TrackerDAO {

	public List<Movie> getUserMovies(int user_id);
	public boolean addUserMovie(int user_id, int movie_id);
	public boolean removeUserMovie(int user_id, int movie_id);
	public boolean userMovieStatus(int user_id, int movie_id, int status);
	public boolean userMovieRating(int user_id, int movie_id, int rating);
	
	
	
}
