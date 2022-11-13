package View.moviegoer;

import static Controller.IOController.*;
import static Controller.Manager.getMovieShowtime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import Controller.IOController;
import Model.Constant;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import View.View;

/**
 * Shows the showtime view.
 *
 * @version 1.0
 */
public class ShowtimeView extends View {
    private Movie movie;

    ShowtimeView(Movie movie) {
        this.movie = movie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Shows the main menu.
     */
    private void displayMenu() {
        Date today = new Date();
        Date tomorrow = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
        Date afterTomorrow = new Date(new Date().getTime() + 2* 24 * 60 * 60 * 1000);
        Date dateChosen;

        if (movie.getMovieStatus().equals(Constant.MovieStatus.NOW_SHOWING)) {
        	System.out.println("1. " + formatTimeMMdd(today) + "\n"+
                    "2. " + formatTimeMMdd(tomorrow) + "\n"+
                    "3. " + formatTimeMMdd(afterTomorrow) + "\n"+
                    "Please choose a date:");
            switch (readChoice(1, 3)) {
                case 1:
                    dateChosen = today;
                    break;
                case 2:
                    dateChosen = tomorrow;
                    break;
                default:
                    dateChosen = afterTomorrow;
                    break;
            }
        }
        else {
        	System.out.println("1. " + formatTimeMMdd(tomorrow)+
                    "\n2. " + formatTimeMMdd(afterTomorrow)+
                    "\nPlease choose a date:");
            switch (readChoice(1, 2)) {
                case 1:
                    dateChosen = tomorrow;
                    break;
                default:
                    dateChosen = afterTomorrow;
                    break;
            }
        }

        System.out.println("Showtime on " + formatTimeMMdd(dateChosen));

        ArrayList<Showtime> showtimeList = new ArrayList<>();
        showtimeList.sort(Comparator.comparing(o -> o.getCinema().getCineplex().toString()));

        if (getMovieShowtime(movie) != null) {
            for (Showtime s : getMovieShowtime(movie)) {
                if (dateEquals(s.getTime(), dateChosen)) showtimeList.add(s);
            }
        }

        if (showtimeList.isEmpty()) {
        	System.out.println("No showtime on that day.\n"+
                    "Press ENTER to go back");
            readString();
            destroy();
            return;
        }

        int index = 0;
        for (Showtime s : showtimeList) {
            System.out.println(++index + ": " + s);
        }

        System.out.println("Please choose a showtime (enter 0 to go back):");

        System.out.println();
        int choice = readChoice(0, showtimeList.size());
        if (choice == 0) {
            destroy();
            return;
        }

        Showtime showtime = showtimeList.get(choice - 1);
        displayShowtimeDetailMenu(showtime);

    }

    /**
     * Shows the showtime details.
     * @param showtime the showtime
     */
    private void displayShowtimeDetailMenu(Showtime showtime) {
    	System.out.println(showtime.toString());
    	System.out.println("1. Check seat availability\n"+
                "2. Book seat\n"+
                "3. Check price\n"+
                "4. Go back");

        int choice = IOController.readChoice(1, 4);
        switch (choice) {
            case 1:
                displaySeat(showtime.getSeats());
                displayShowtimeDetailMenu(showtime);
                break;
            case 2:
                displaySeat(showtime.getSeats());
                displayBookSeatMenu(showtime);
                break;
            case 3:
                showPrice(showtime);
                break;
            case 4:
                destroy();
                break;
        }
    }

    /**
     * Shows the price of a showtime.
     * @param showtime the showtime
     */
    private void showPrice(Showtime showtime) {
        double basePrice = showtime.getCinema().getBasePrice();
        Movie movie = showtime.getMovie();
        System.out.println("Ticket price for " + movie.getTitle() + " (" + (showtime.getCinema().is3D() ? "3D" : "Digital") + ")");
        System.out.printf("Weekdays" + "Weekends\n" +
                "Regular    %-8.2f        %-8.2f\n" +
                "Senior Citizens     %-8.2f        %-8.2f\n\n", basePrice, basePrice * 1.2, basePrice * 0.5, basePrice * 0.5 * 1.2);
        
        System.out.println();
        readString("Press ENTER to go back");
        displayShowtimeDetailMenu(showtime);
    }

    /**
     * Shows the seat layout of a showtime.
     * @param seats the seat layout
     */
    private void displaySeat(Seat[][] seats) {
        System.out.println("                    -------Screen------");
        System.out.println("     1  2  3  4  5  6  7  8     9 10 11 12 13 14 15 16");
        for (int row = 0; row <= 8; row++) {
            System.out.print(row + 1 + "   ");
            for (int column = 0; column <= 16; column++) {
                if (seats[row][column] == null) System.out.print("   ");
                else System.out.print(seats[row][column]);
            }
            System.out.println();
        }
        System.out.println();
        readString("Press ENTER to continue:");
    }

    /**
     * Shows the booking seat menu.
     * @param showtime the showtime to be booked
     */
    private void displayBookSeatMenu(Showtime showtime) {
        int row, column;

        System.out.println("Enter the row (1 - 9) of the seat:");
        row = IOController.readChoice(1, 9);
        System.out.println("Enter the column (1 - 16) of the seat:");
        column = IOController.readChoice(1, 16);

        if (showtime.getSeatAt(row, column) == null) {
            System.out.println("Invalid choice. Please try again.");
            displayBookSeatMenu(showtime);
        }
        else if (showtime.getSeatAt(row, column).isBooked()) {
            System.out.println("Seat already booked. Please choose another seat.");
            displayBookSeatMenu(showtime);
        }
        else {
            System.out.println(showtime.getMovie().getSales());
            intent(this, new Booking(showtime.getSeatAt(row, column)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        ((MovieListing)(getPrevView())).start(movie);
    }
}
