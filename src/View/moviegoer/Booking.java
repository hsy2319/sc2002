package View.moviegoer;

import static Controller.IOController.*;
import static Controller.Manager.*;

import Controller.Manager;
import Model.*;
import View.View;

/**
 * Shows the booking view.
 *
 * @version 1.0
 */
public class Booking extends View {
    private Seat seat;
    private String ticketType;
    private double basePrice;
    private boolean bookingFinished;

    public Booking(Seat seat) {
        this.seat = seat;
        basePrice = seat.getShowtime().getCinema().getBasePrice();
        bookingFinished = false;
        getBasePrice();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (bookingFinished) destroy();
        else showMenu();
    }

    /**
     * Shows the booking menu.
     */
    private void showMenu() {
    	System.out.println("Booking detail\n");
        printBookingDetail();
        System.out.println("1. Proceed\n"+
                "2. Go back");
        int choice = readChoice(1, 2);
        switch (choice) {
            case 1:
                getCustomerInformation();
                break;
            case 2:
                destroy();
                break;
        }
    }

    /**
     * Gets the base price considering weekend, weekday or holiday.
     */
    private void getBasePrice() {
        Holiday holiday = getHoliday(seat.getShowtime().getTime());
        if (holiday != null) {
            double rate = holiday.getRate();
            basePrice = rate * basePrice;
            ticketType = holiday.getName();
        }
        else {
            if (isWeekend(seat.getShowtime().getTime())) {
                basePrice = basePrice * 1.2;
                ticketType = "Weekend";
            }
            else {
                ticketType = "Weekday";
            }
        }
    }

    /**
     * Prints the booking detail
     */
    private void printBookingDetail() {
        Showtime showtime = seat.getShowtime();
        Movie movie = showtime.getMovie();
        Cinema cinema = showtime.getCinema();

        System.out.println(movie.getTitle() + " (" + (showtime.getCinema().is3D() ? "3D" : "Digital") + ")");
        System.out.println(movie.getAgeRestriction());
        System.out.println("Cinema: " + cinema + " (" + cinema.getCineplex() + ")");
        System.out.println("Showing on " + formatTimeMMddkkmm(showtime.getTime()));
        System.out.println("Seat: Row " + (seat.getRow()+1) + " Col " + ((seat.getCol() > 8) ? seat.getCol() : (seat.getCol()+1)));
        System.out.println();
        System.out.println("Ticket type: " + ticketType);
        System.out.println("Ticket price: " + round(basePrice, 2) + " SGD (Excl. GST)");
    }

    /**
     * Asks the user to enter their information
     */
    private void getCustomerInformation() {
        String name = readString("Please enter your name:");
        String mobile = readString("Please enter your mobile number:");
        String email = readEmail("Please enter your email address:");
        boolean isSeniorCitizen = askConfirm("Are you a senior citizen?",
                                    "Enter Y if yes:");

        Moviegoer customer = new Moviegoer(name, mobile, email, isSeniorCitizen);

        bookingFinished = true;
        intent(this, new Payment(customer, seat, basePrice));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        ((MovieListing)(prevView.prevView)).start(
                Manager.getMovieListing().get(Manager.getMovieListing().indexOf(
                        seat.getShowtime().getMovie())));
    }
}
