package main;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.*;

public class MovieDisplay {
    public static Movie selectMovieByID(ArrayList<Movie> m_list, int id){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getId() == id){
                return m_list.get(i);
            }
        }
        return null;
    }
    
    public static Movie searchMovieByName(ArrayList<Movie> m_list, String input){
        for (int i = 0; i < m_list.size(); i++){
            if (m_list.get(i).getName().toLowerCase().contains(input.toLowerCase())){
                return m_list.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Movie> getAllMovies(){
        return Database.loadMovies("");
    }
    
    public static ArrayList<Movie> getNowShowing(){
    	ArrayList<Movie> movies = getAllMovies();
		ArrayList<Movie> nowShowingMovies = new ArrayList<Movie>();
    	for (Movie m:movies) {
			if (m.getStatus().equals("Now Showing")) {
				nowShowingMovies.add(m);
			}
		}
    	return nowShowingMovies;
    }

    public static double getAvgRating(Movie movie){
        double averageRating;
        if(movie.getRating().size()>=1){
            averageRating = 0;
            for(int i = 0; i < movie.getRating().size(); i++)
                averageRating += movie.getRating().get(i);
            return averageRating / movie.getRating().size();
        }
        return -1;
    }

}
