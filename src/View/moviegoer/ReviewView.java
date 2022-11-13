package View.moviegoer;

import java.io.IOException;
import java.util.ArrayList;

import Controller.Manager;
import Model.Movie;
import Model.Review;
import View.View;

import static Controller.IOController.*;
import static Controller.Manager.*;
import static Model.Constant.MovieStatus.*;

/**
 * Shows the review view.
 */
public class ReviewView extends View{
    private Movie movie;

    public ReviewView(Movie movie) {
        this.movie = movie;
    }

    /**
     * {@inheritDoc}
     */
    protected void start(){
        displayMenu();
    }

    /**
     * Shows the main menu.
     */
    private void displayMenu() {
    	System.out.println("Review");
        if (movie.getMovieStatus() == COMING_SOON) {
        	System.out.println("Cannot review coming soon movies.\n"+
                    "Press ENTER to go back.\n"+
                    "\n");
            destroy();
        }
        System.out.println("1. Write review\n"+
                "2. View reviews\n"+
                "3. Go back");
        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                addReview();
                break;
            case 2:
                listReview();
                break;
            case 3:
                destroy();
                break;
        }
        destroy();
    }

    /**
     * Adds a review.
     */
    private void addReview(){
    	System.out.println("Write Review:");
        String name = readString("Your name:");
        System.out.println("Enter rating: (integer between 1 ~ 5)");
        int rating = readChoice(1, 5);
        String content = readString("Enter your comments:");
        Review review = new Review(this.movie, rating, content, name);

        try {
            addNewReview(movie, review);
            System.out.println("Successfully created review for " + movie.getTitle());
        }
        catch (IOException ex) {
            System.out.println("Failed to create review for " + movie.getTitle());
        }
        finally {
            start();
        }
    }

    /**
     * Lists all reviews of the movie.
     */
    private void listReview(){
    	System.out.println("Reviews for " + movie.getTitle());
        ArrayList<Review> reviewList = Manager.getReviewList(movie);

        if (reviewList != null){
            int index = 0;
            for (Review r : reviewList) {
                System.out.println(++index + " Name:     " + r.getName());
                System.out.println("  Date:     " + formatTimeMMddkkmm(r.getDate()));
                System.out.println("  Rating:   " + r.getRating());
                System.out.println("  Comments: " + wrapText(r.getContent(), 45, 12));
                System.out.println();
            }
        }
        else{
            System.out.println("No reviews available.");
        }
        readString("Press ENTER to go back.", "");
        start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        ((MovieListing)(getPrevView())).start(movie);
    }
}
