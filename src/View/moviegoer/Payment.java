package View.moviegoer;

import static Controller.IOController.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.Manager;
import Model.BookingHistory;
import Model.Movie;
import Model.Moviegoer;
import Model.Seat;
import View.View;

/**
 * Shows the payment view.
 *
 * @version 1.0
 */
public class Payment extends View {
    private Seat seat;
    private Moviegoer customer;
    private String TID;
    private double basePrice;
    private double GST;
    private double totalPrice;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    Payment(Moviegoer customer, Seat seat, double basePrice) {
        this.customer = customer;
        this.seat = seat;
        this.basePrice = basePrice;
        generateTID();
        getTotalPrice();
    }

    /**
     * Generates the TID based on time.
     */
    private void generateTID() {
        TID = seat.getShowtime().getCinema().getCode() +
                new SimpleDateFormat("YYYYMMddhhmm").format(new Date().getTime())
        ;
    }

    /**
     * Gets the total price.
     */
    private void getTotalPrice() {
        if (customer.isSeniorCitizen()) basePrice /= 2;
        GST = round((basePrice + 2) * 0.07, 2);
        totalPrice = round(basePrice + 2 + GST, 2);
    }

    /**
     * Shows the payment menu.
     */
    private void displayMenu() {
    	System.out.println("Payment");
    	System.out.println("Transaction ID: " + TID +
                "\nTicket price: " + basePrice+
                "\nBooking fee: 2.0"+
                "\nGST: " + GST+
                "\nTotal price: " + totalPrice
                );
        if (customer.isSeniorCitizen()) {
        	System.out.println("(50% off for senior citizen)");
        }

        System.out.println("1. Confirm payment\n"+
                "2. Cancel"
                );

        int choice = readChoice(1, 2);
        switch (choice) {
            case 1:
                logBooking();
                break;
            case 2:
                destroy();
                break;
        }
    }

    /**
     * Adds booking to booking history.
     */
    private void logBooking() {
        try {
            seat.bookSeat();
            Movie movie = seat.getShowtime().getMovie();
            Manager.getMovieListing().get(Manager.getMovieListing().indexOf(movie)).incrementSales();
            BookingHistory record = new BookingHistory(TID, customer, seat);
            Manager.addBooking(record);
            Manager.updateShowtime();
            Manager.updateMovieListing();
            System.out.println("Payment successful.");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Payment failed.");
        }

        destroy();
    }
}
