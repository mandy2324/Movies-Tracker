package com.cognixia.jump.DAO;

import java.sql.Time;

public class Movie {

	//Attributes for Movie to operate
	private int id;
	private String title;
	private Time length;
	private String summary;
	private String genre;
	
	//Constructor
	public Movie(int id, String title, Time length, String summary, String genre) {
		super();
		this.id = id;
		this.title = title;
		this.length = length;
		this.summary = summary;
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

	public Time getLength() {
		return length;
	}

	public void setLength(Time length) {
		this.length = length;
	}
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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
