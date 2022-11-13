package View.moviegoer;

import static Controller.IOController.*;
import static Controller.Manager.*;

import java.util.ArrayList;

import Model.BookingHistory;
import View.View;

/**
 * Shows the booking history view.
 *
 * @version 1.0
 */
public class BookingHistoryView extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Shows the booking history menu.
     */
    private void displayMenu() {
    	System.out.println("Booking history");
        ArrayList<BookingHistory> bookingHistory = getBookingHistory();

        if (bookingHistory == null || bookingHistory.isEmpty()) {
            readString("No history.",
                    "Press ENTER to go back.", "");
        }
        else {
            for (BookingHistory record : bookingHistory) {
                System.out.println(record);
            }
            readString("Press ENTER to go back.", "");
        }

        destroy();
    }
}
