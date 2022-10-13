package com.cognixia.jump.DAO;

import java.util.List;

public interface MovieDAO {

	public List<Movie> getAllMovies();
	public Movie getMovieById();
	public Movie getMovieByTitle();
}
