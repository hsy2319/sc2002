package Model;

import static Controller.IOController.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Contains information of a showtime: {@code Movie}, {@code Cinema},
 * {@code Date} and an array of {@code Seat}.
 *
 * @version 1.0
 */
public class Showtime implements Serializable {
    private Movie movie;
    private Cinema cinema;
    private Date time;
    private Seat[][] seats;

    /** number of total columns */
    private final static int COLUMNS = 17;
    /** number of total rows */
    private final static int ROWS = 9;

    public Showtime() {
        seats = new Seat[ROWS][COLUMNS];
        initializeSeat();
    }

    /**
     * Sets time of the showtime.
     * @param time an {@code Data} object, the time of the showtime
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Sets cinema of the showtime.
     * @param cinema the cinema of the showtime
     */
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * Sets movie of the showtime.
     * @param movie the movie of the showtime
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the movie of the showtime.
     * @return the movie of the showtime
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Gets the cinema of the showtime.
     * @return the cinema of the showtime
     */
    public Cinema getCinema() {
        return cinema;
    }

    /**
     * Gets the seat array.
     * @return the seat array of the showtime
     */
    public Seat[][] getSeats() {
        return seats;
    }

    /**
     * Gets the seat in array
     * @param row the row number of the seat
     * @param column the column number of the seat
     * @return the seat at ({@code row}, {@code column})
     */
    public Seat getSeatAt(int row, int column) {
        if (row < 1 || row > 9 || column < 1 || column > 16) return null;

        if (column >= 9) column++;

        return seats[row - 1][column - 1];
    }

    /**
     * Gets the time of the showtime.
     * @return the time of the showtime
     */
    public Date getTime() {
        return time;
    }

    /**
     * Initializes {@code Seats}.
     */
    private void initializeSeat() {
        for (int row = 0; row <= 3; row++) {
            for (int column = 2; column <= 16; column++) {
                if (column == 8) continue;
                seats[row][column] = new Seat(row, column, this);
            }
        }

        for (int row = 4; row <= 7; row++) {
            for (int column = 0; column <= 16; column++) {
                if (column == 8) continue;
                seats[row][column] = new Seat(row, column, this);
            }
        }

        for (int column = 0; column <= 16; column++) {
            if (column == 8 || column == 9 || column == 10) continue;
            seats[8][column] = new Seat(8, column, this);
        }
    }

    /**
     * Gets a {@code String} of details of the showtime
     * @return a {@code String} of details of the showtime
     */
    public String getDetails() {
        return "Cineplex: " + cinema.getCineplex() + "\n" +
                "Cinema: " + cinema.toString() + "\n" +
                "Time: " + time.toString() + "\n";
    }



    @Override
    public String toString() {
        return cinema.getCineplex().toString() + ": " + formatTimeMMddkkmm(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Showtime showtime = (Showtime) o;

        if (!movie.equals(showtime.movie)) return false;
        if (!cinema.equals(showtime.cinema)) return false;
        return formatTimeMMddkkmm(time).equals(formatTimeMMddkkmm(showtime.time));
    }

    @Override
    public int hashCode() {
        int result = movie.hashCode();
        result = 31 * result + cinema.hashCode();
        result = 31 * result + formatTimeMMddkkmm(time).hashCode();
        return result;
    }
}
