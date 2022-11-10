package Model;

import java.io.Serializable;
import java.util.Date;

import static Controller.IOController.*;

/**
 * Contains information of a holiday: name, date
 * and price rate.
 *
 * @version 1.0
 */
public class Holiday implements Serializable {
    private String name;
    private Date date;
    private double rate;

    public Holiday(String name, Date date, double rate) {
        this.name = name;
        this.date = date;
        this.rate = rate;
    }

    /**
     * Gets the name of the holiday.
     * @return the name of the holiday
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the holiday.
     * @return the date of the holiday
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the price rate of the holiday.
     * @return the price rate of the holiday
     */
    public double getRate() {
        return rate;
    }

    /**
     * Returns a {@code String} of the holiday detail.
     * @return a {@code String} of the holiday detail
     */
    public String printDetail() {
        return "Date      : " + formatTimeMMdd(date) + "\n" +
                "Price rate: " + rate;
    }

    @Override
    public String toString() {
        return name + " (" + formatTimeMMdd(date) + ")";
    }
}
