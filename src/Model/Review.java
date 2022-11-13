package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Contains information of a review:
 * date, rating, content, {@code Movie} and reviewer's
 * name.
 *
 * @version 1.0
 */
public class Review implements Serializable {
    private final Date date;
    private final int rating;
    private final String content;
    private final Movie movie;
    private final String name;

    /** maximum rating possible */
    private static final int maxRating = 5;

    /** minimum rating possible */
    private static final int minRating = 1;

    public Review(Movie movie, int rating, String content, String name) {
        if(rating > maxRating) this.rating = maxRating;
        else if (rating < minRating) this.rating = minRating;
        else this.rating = rating;

        this.date = new Date();
        this.content = content;
        this.movie = movie;
        this.name = name;
    }

    /**
     * Gets the {@code Movie} reviewed.
     * @return the movie reviewed
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Gets the content of the review.
     * @return the content of the review
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the rating given.
     * @return the rating given
     */
    public int getRating() {
        return rating;
    }

    /**
     * Get the reviewer's name.
     * @return the reviewer's name
     */
    public String getName() { return name; }

    /**
     * Gets the date of the review.
     * @return the date of the review
     */
    public Date  getDate() { return date; }

}

