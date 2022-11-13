package View;

import static Controller.IOController.*;

import View.moviegoer.BookingHistoryView;
import View.moviegoer.MovieListing;

/**
 * The view shown to moviegoer.
 *
 * @version 1.0
 */
public class MoviegoerView extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
    	System.out.println("Moviegoer\n");
    	System.out.println("Please choose:\n"+
                "1. Search or list movies\n"+
                "2. View booking history\n"+
                "3. Go back");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                intent(this, new MovieListing());
                break;
            case 2:
                intent(this, new BookingHistoryView());
                break;
            case 3:
                destroy();
                break;
        }
    }
}