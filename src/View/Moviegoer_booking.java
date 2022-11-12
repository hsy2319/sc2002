// show now showing movies, show showtimes, allow moviegoer to select showtime, seat and book
package View;
import java.util.ArrayList;
import java.util.Scanner;

import main.Movie;

public class Moviegoer_booking extends View{
	
	public Moviegoer_booking() {}
	public void start(Scanner scan) {
		System.out.println("Now showing movies:");
		/* add back when Movie accessors fixed
		ArrayList<Movie> movies = MovieDisplay.getNowShowing();
		*/
		//remove when fixed:
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie(1,"Bee","English",new ArrayList<Integer>(),"1.5",new ArrayList<String>(),"aca","aca",new ArrayList<String>(),"avvdsx"));
		movies.add(new Movie(2,"Cee","English",new ArrayList<Integer>(),"1.5",new ArrayList<String>(),"aca","aca",new ArrayList<String>(),"avvdsx"));
		movies.add(new Movie(3,"Dee","English",new ArrayList<Integer>(),"1.5",new ArrayList<String>(),"aca","aca",new ArrayList<String>(),"avvdsx"));
		//until here
		for (int i=0;i<movies.size();i++) {
			System.out.println((i+1)+". "+movies.get(i).getName());
		}
		System.out.print("Choose movie to book:");
		/* to do: when showtime class done:
		 * check seat availability + choose seat (seats.csv)
		 * seats.csv: [showtime code, occupied seats]
		 *  booking (update bookings file: name, mobile no., email, tid)
		 *  bookings.csv: [name, tid]
		 *  print ticket
		 */
	}
}
