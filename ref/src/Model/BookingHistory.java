package Model;

import java.io.Serializable;

/**
 * Contains information of booking history:
 * TID (Transaction ID), customer and the seat booked.
 *
 * @version 1.0
 */
public class BookingHistory implements Serializable {
    private final String TID;
    private final Moviegoer customer;
    private final Seat seat;

    /**
     * Creates a {@code BookingHistory} object with
     * TID (Transaction ID), customer and the seat booked.
     * @param TID Transaction ID
     * @param customer customer
     * @param seat the seat booked
     */
    public BookingHistory(String TID, Moviegoer customer, Seat seat) {
        this.TID = TID;
        this.customer = customer;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return TID + "\n" +
                "Name: " + customer.getName() + "\n" +
                "Mobile: " + customer.getMobile() + "\n" +
                "Email: " + customer.getEmail() + "\n" +
                "Movie: " + seat.getShowtime().getMovie().getTitle() + "\n" +
                seat.getShowtime().getDetails() +
                "Seat: Row " + (seat.getRow()+1) + " Col " + ((seat.getCol() > 8) ? seat.getCol() : (seat.getCol()+1)) + "\n";
    }
}
