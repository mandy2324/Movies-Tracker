package com.cognixia.jump.DAO;

import java.util.List;

public interface TrackerDAO {

	public List<Movie> getUserMovies(int user_id);
	public boolean addUserMovie(Tracker tracked);
	public boolean removeUserMovie(int user_id, int movie_id);
	public boolean userMovieStatus(Tracker tracked, int status);
	public boolean userMovieRating(Tracker tracked, int rating);
	
	
	
}
