package View;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
				+ "4. Go back\\n");
		int choice = readChoice(1,4);
		switch(choice) {
		case 1:
			System.out.println("Enter the title you wish to search for: ");
			String movie_choice = scan.next();
			searchMovie(movie_choice);
			break;
		case 2:
			//List all movies
			Boundary.displayMovie(MovieDisplay.getAllMovies());
			System.out.println("Enter movie id to see movie details");
			int movie_id = readChoice(10,13); //hard coding for now 
			//Boundary.displayMovie(null);
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
	
	public void searchMovie(String movie_choice) {
		//implement search function
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
	
	public void moviedetails(int movie_id) {
		
	}
    
}
