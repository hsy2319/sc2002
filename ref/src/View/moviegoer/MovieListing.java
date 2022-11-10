package View.moviegoer;

import Model.Constant;
import Model.Movie;
import View.View;
import java.util.*;

import static Controller.Manager.*;
import static Controller.IOController.*;

/**
 * Shows the movie view.
 *
 * @version 1.0
 */

public class MovieListing extends View {
    private boolean topFive = false;
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Starts the movie detail menu of specified {@code Movie}.
     * @param movie the movie whose detail to be displayed
     */
    protected void start(Movie movie) {
        displayMovieDetailMenu(movie);
    }

    /**
     * Shows the main menu.
     */
    private void displayMenu() {
        printTitle("Search or list movies");
        printChoices("1. Search movies",
                "2. List all movies",
                "3. List the top 5 movies",
                "4. Go back","");
        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                searchMovie();
                break;
            case 2:
                topFive = false;
                displayMovieListing();
                break;
            case 3:
                topFive = true;
                displayMovieListing();
                break;
            case 4:
                break;
        }

        destroy();
    }

    /**
     * Searches a movie and shows the result of all matching titles.
     */
    private void searchMovie() {
        String input = readString("Enter the movie title:");
        ArrayList<Movie> searchResult = getMovieByTitle(input);
        if (searchResult.isEmpty()) {
            printChoices("-> No results found.",
                    "Press ENTER to go back", "");
            readString();
            displayMenu();
        }
        else {
            printChoices("-> " + searchResult.size() + " results found:");
            int index = 0;
            for (Movie movie : searchResult) {
                printChoices(++index + ". " + movie.getTitle() + " (" + movie.getMovieStatus().toString() + ")");
            }
            printChoices(index + 1 + ". Go back", "");

            int choice = readChoice(1, index + 1);
            if (choice == index + 1) start();
            else displayMovieDetailMenu(searchResult.get(choice - 1));
        }
    }

    /**
     * Shows the movie listing.
     * Display top 5 ranking movie if {@code topFive} is true
     */
    private void displayMovieListing() {
        ArrayList<Movie> movieListing;

        if (topFive) movieListing = getTop5MovieListing();
        else movieListing = getMovieListing();

        printTitle("Movies");
        if (movieListing.isEmpty()) {
            printChoices("Movie listing is not available");
            displayMenu();
        }

        int index = 0;

        if (!topFive || getSystem().get("movieOrder")) {  // show movie rating
            for (Movie movie : movieListing) {
                if (movie.getMovieStatus().equals(Constant.MovieStatus.END_OF_SHOWING)) continue;
                printChoices(++index + ". " + movie.getTitle() + generateSpaces(37 - movie.getTitle().length())
                        + "(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (getMovieRating(movie) == 0.0 ? "No rating" : getMovieRating(movie)) + "]");
            }
        }
        else {
            for (Movie movie : movieListing) {  // show ticket sales
                if (movie.getMovieStatus().equals(Constant.MovieStatus.END_OF_SHOWING)) continue;
                printChoices(++index + ". " + movie.getTitle() + generateSpaces(37 - movie.getTitle().length())
                                + "(" + movie.getMovieStatus().toString() + ") " +
                        "[" + (movie.getSales() == 0 ? "No sale" : movie.getSales()) + "]");
            }
        }

        printChoices(index + 1 + ". Go back", "");

        int choice = readChoice(1, index + 1);

        if (choice == index + 1) start();
        else {
            Movie movie = movieListing.get(choice - 1);
            if (movie.getMovieStatus().equals(Constant.MovieStatus.END_OF_SHOWING)) {
                movie = movieListing.get(choice);
            }
            displayMovieDetailMenu(movie);
        }

    }

    /**
     * This method is to display the detail of the movie and other operations.
     * @param movie the movie whose detail to be displayed
     */
    private void displayMovieDetailMenu(Movie movie) {
        printTitle("Movie details");
        printChoices(movie.toString(),
                "1. Display showtime",
                "2. Display/write reviews",
                "3. Go back", "");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                intent(this, new ShowtimeView(movie));
                break;
            case 2:
                intent(this, new ReviewView(movie));
                break;
            case 3:
                break;
        }
        displayMovieListing();
    }
}