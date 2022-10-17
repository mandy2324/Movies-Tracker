package com.cognixia.jump.userlogin;

import java.util.Scanner;

import com.cognixia.jump.DAO.TrackerDAOClass;
import com.cognixia.jump.DAO.User;

public class loginmenu {

	public static void main(String[] args) {
		
		Scanner Scan = new Scanner(System.in);
		
		//User login Part
		UserLogin ul= new UserLogin();
		User usr=ul.userPrompt(Scan);
		
		System.out.println(usr);
		
		//Tracker part
		TrackerDAOClass tdao =new TrackerDAOClass();
		
		//Get User Movies from tracker class;
		
		tdao.getUserMovies(usr.getId());
		
		
		// Tracker object would be needed from now on
		
//		//Tracker tck= new Tracker(movie_id,usr.getId());
//		
//		tdao.removeUserMovie(tck);
//		tdao.addUserMovie(tck);
//		tdao.userMovieRating(null, 0);
//		tdao.userMovieStatus(null, 0);
		
		
	}

	
	
}
