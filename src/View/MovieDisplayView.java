package View;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import static Controller.IOController.printChoices;
import static Controller.IOController.printTitle;

/**
 *
 * @author Kenneth adapted from code by Essh
 */
import java.util.*;
import main.*;
public class MovieDisplayView extends View{
	public void start(Scanner scan) {
		System.out.printf(""
				+ "1. Search movie\n"
				+ "2. List all showing movies\n"
				+ "3. List top 5 movies\n"
				+ "4. Go back\n");
		int choice = readChoice(1,4);
		switch(choice) {
		case 1:
			//search for a particular movie
			searchMovie();
			break;
		case 2:
			//List all movies
			Boundary.displayMovie(MovieDisplay.getAllMovies());
			System.out.println("Enter movie id to see movie details");
			int movie_id = readChoice(10,20); //hard coding for now 
			moviedetails(MovieDisplay.selectMovieByID(MovieDisplay.getAllMovies(),movie_id));
			break;
		case 3:
			//List top 5 movies
			topfiveMovie();
			break;
		case 4:
			destroy();
			break;
		}
	}
	
	public void searchMovie() {
		//implement search function
		System.out.println("Enter the title you wish to search for: ");
		String movie_choice = scan.next();
		Movie movie_found = MovieDisplay.searchMovieByName(MovieDisplay.getAllMovies(), movie_choice);
		if (movie_found == null) System.out.println("Movie not found");
		else{
			Boundary.displayMovie(movie_found);
			moviedetails(movie_found);
		}
		
	}
	
	public void topfiveMovie() {
		//implement top five movie function;
		SystemSettingsView settingsview= new SystemSettingsView();
		if(settingsview.getRankbyTix() == true) {
			//rank by rating
		}
		else if (settingsview.getRankbyTix() == false) {
			//rank by ticket sales
		}
	}
	
	private void moviedetails(Movie movie) {
		System.out.println("Movie details");
        System.out.println(movie.getName()
        		+ "1. Display showtime"
                + "2. Display/write reviews"
                + "3. Go back");
        int choice = readChoice(1,3);
        switch(choice) {
        case 1:
        	branch(this, new ShowtimeView(movie));
        	break;
        case 2:
        	branch(this, new ReviewView(movie));
        	break;
        case 3:
        	break;
        }
	}
    
}
