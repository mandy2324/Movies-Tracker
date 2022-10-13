package com.cognixia.jump.DAO;

public class Movie {

	//Attributes for Movie to operate
	private int id;
	private String title;
	private int length;
	private String genre;
	
	//Constructor
	public Movie(int id, String title, int length, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.length = length;
		this.genre = genre;
	}

	//Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", length=" + length + ", genre=" + genre + "]";
	}
	
	
	
}
