package Model;

import java.io.Serializable;

/**
 * Contains information of a seat: row number, column number,
 * {@code Showtime} and if seat is booked.
 *
 * @version 1.0
 */
public class Seat implements Serializable{
    private final int row;
    private final int column;
    private final Showtime showtime;
    private boolean isBooked;

    public Seat(int row, int column, Showtime showtime) {
        this.row = row;
        this.column = column;
        this.showtime = showtime;
        isBooked = false;
    }

    /**
     * Gets the showtime.
     * @return showtime of the seat
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * Gets column number.
     * @return column number
     */
    public int getCol() {
        return column;
    }

    /**
     * Gets row number.
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Check if seat is booked.
     * @return true if the seat is booked, else false
     */
    public boolean isBooked() {
        return isBooked;
    }

    /**
     * Books the seat by setting {@code booked} as true
     */
    public void bookSeat() {
        isBooked = true;
    }

    @Override
    public String toString() {
        if (!isBooked) return "[ ]";
        else return "[*]";
    }
}
