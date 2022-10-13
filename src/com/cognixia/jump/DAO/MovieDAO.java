package com.cognixia.jump.DAO;

import java.util.List;

public interface MovieDAO {

	//Methods for manipulating movie data
	public List<Movie> getAllMovies();
	public Movie getMovieById(int movieId);
	public Movie getMovieByTitle(String title);
	public Movie showMovie(int id);
	
}
