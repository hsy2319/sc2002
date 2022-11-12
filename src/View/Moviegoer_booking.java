// show now showing movies, show showtimes, allow moviegoer to select showtime, seat and book
/*package View;
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
*/
//UPDATED BY QIUZHEN
import java.util.Scanner;

public class MovieGoer_Booking extends View {
		//Variables
			private Seat seat;
			private double basePrice;
			private String ticketType;
			private boolean isBooked;


		//Constructor
			public MovieGoer_Booking(Seat seat){
				this.seat=seat;
				basePrice= //TO ADD seat.showtime
				
				
						
			
		}
		//Methods
			
			public void displayMenu() {
				
				
			}
			
			
			private void getBasePrice() {
				//Check if holiday or weekend
				
			}
			
			private void printBookingDetail() {
				//print name of movie, date and time, location, seat
				//rating of movie
				
				
			}
			
			private void promptCustomerInformation() {
				Scanner sc = new Scanner(System.in);
				String name;
				String email;
				String mobile;
				boolean isSenior;
				String isSenAns;
				
		        System.out.println("Please enter your name:");
		        name=sc.toString();
		        System.out.println("Please enter your mobile number:");
		        mobile=sc.toString();
		        System.out.println("Please enter your Email address:");
		        email=sc.toString();
		        System.out.println("Enter Yes if you are a senior citizen or a student?");
		        System.out.println("Please enter YES if applies and NO otherwise");
		        isSenAns=sc.toString();
		        if(isSenAns=="YES") isSenior=true;
		
		        // Create moviegoer_data object
		        MovieGoer_data cust = new MovieGoer_data(name, mobile, email, isSenior);

		        // proceed to payment how

		    }


}

