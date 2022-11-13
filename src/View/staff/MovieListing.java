package View.staff;

import java.io.IOException;
import java.util.ArrayList;

import Model.Movie;
import Model.Constant.*;
import View.View;

import static Controller.IOController.*;
import static Controller.Manager.*;
import static Model.Constant.MovieStatus.*;

/**
 * Shows the movie listing view.
 *
 * @version 1.0
 */
public class MovieListing extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Shows the main menu of movie listing.
     */
    private void displayMenu() {
        ArrayList<Movie> movieListing;
        movieListing = getMovieListing();

        int index = 0;
        System.out.println("Modify movie listing\n");
        if (movieListing.isEmpty()) {
        	System.out.println("No movie.\n"+
                    "1. List a new movie\n"+
                    "2. Go back");
            int choice = readChoice(1, 2);
            if (choice == 1) addMovieListing();
            else destroy();
            return;
        }

        for (Movie movie : movieListing) {
            System.out.println(++index + ". " + movie.getTitle() + "(" + movie.getMovieStatus().toString() + ")");
        }
        System.out.println(index + 1 + ". Go back");

        System.out.println("Choose a movie to modify.\n"+
                "To list a new movie, enter 0:");

        int choice = readChoice(0, index + 1);

        if (choice == index + 1) destroy();
        else if (choice == 0) {
            addMovieListing();
        }
        else displayMovieDetailMenu(movieListing.get(choice - 1));
    }

    /**
     * Adds a movie listing.
     */
    private void addMovieListing() {
        String title, director, synopsis;
        AgeRestriction ageRestriction = null;
        ArrayList<String> cast;
        MovieStatus movieStatus = null;

        System.out.println("Add movie listing\n");
        title = readString("Enter the title:");

        // set age restriction
        while (ageRestriction == null) {
            String input = readString("Choose the movie restriction, please enter one of the following:",
                    "G, PG, PG13, NC16, M18, R21").toUpperCase();
            ageRestriction = readAgeRestriction(input);
        }

        director = readString("Enter director:");
        synopsis = wrapText(readString("Enter synopsis:"), 50, 14);

        // set casts
        String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
        cast = new ArrayList<>();
        for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);

        // set movie movieStatus
        while(movieStatus == null) {
            String input = readString("Enter movie status, please enter one of the following:",
                    "Coming soon, Now showing, End of showing").toUpperCase();
            movieStatus = readMovieStatus(input);
        }

        // create movie object
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setAgeRestriction(ageRestriction);
        movie.setDirector(director);
        movie.setSynopsis(synopsis);
        movie.setCast(cast);
        movie.setMovieStatus(movieStatus);

        // write to file
        try {
            addNewListing(movie);
            System.out.println("Successfully listed movie " + title);
        }
        catch (IOException ex) {
            System.out.println("Failed to list the movie.");
        }
        finally {
            displayMenu();
        }
    }

    /**
     * Show the movie detail menu.
     * @param movie the movie
     */
    private void displayMovieDetailMenu(Movie movie) {
    	System.out.println("Movie details\n");
    	System.out.println(movie.toString()+
                "\n1. Update movie details\n"+
                "2. Remove the listing\n"+
                "3. Add/drop showtime\n"+
                "4. Go back");

        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                updateMovieDetailsMenu(movie);
                break;
            case 2:
                removeListingMenu(movie);
                break;
            case 3:
               intent(this, new ShowtimeView(movie));
                break;
            case 4:
                displayMenu();
        }
    }

    /**
     * Show the menu of updating movie detail.
     * @param movie the movie
     */
    private void updateMovieDetailsMenu(Movie movie) {
    	System.out.println("Modify movie details\n");
    	System.out.println("1. Change title\n"+
                "2. Change age restriction\n"+
                "3. Change director\n"+
                "4. Change synopsis\n"+
                "5. Change casts\n"+
                "6. Change status\n"+
                "7. Go back");

        int choice = -1;
        while (choice != 7) {
            choice = readChoice(1, 7);
            switch (choice) {
                case 1:
                    movie.setTitle(readString("Enter the title:"));
                    System.out.println("Title changed.");
                    break;
                case 2:
                    AgeRestriction ageRestriction = null;
                    while (ageRestriction == null) {
                        String input = readString("Choose the movie restriction, please enter one of the following:",
                                "G, PG, PG13, NC16, M18, R21").toUpperCase();
                        ageRestriction = readAgeRestriction(input);
                    }
                    movie.setAgeRestriction(ageRestriction);
                    System.out.println("Age restriction changed.");
                    break;
                case 3:
                    movie.setDirector(readString("Enter director:"));
                    System.out.println("Director changed.");
                    break;
                case 4:
                    movie.setSynopsis(wrapText(readString("Enter synopsis"), 50, 10));
                    System.out.println("Synopsis changed.");
                    break;
                case 5:
                    String[] castArray = readString("Enter casts, separate with semicolon(;)").split(";");
                    ArrayList<String> cast = new ArrayList<>();
                    for (int i = 0; i < castArray.length; i++) cast.add(castArray[i]);
                    movie.setCast(cast);
                    System.out.println("Casts changed.");
                    break;
                case 6:
                    if (movie.getMovieStatus().equals(NOW_SHOWING)) {
                        if (askConfirm("Are you sure to change the movie status to Coming Soon?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(COMING_SOON);
                            System.out.println("Movie status changed.");
                        }
                        else {
                            System.out.println("Movie status remains.");
                        }
                    } else {
                        if (askConfirm("Are you sure to change the movie status to Now showing?",
                                "Enter Y to confirm, N to cancel:")) {
                            movie.setMovieStatus(NOW_SHOWING);
                            System.out.println("Movie status changed.");
                        }
                        else {
                            System.out.println("Movie status remains.");
                        }
                    }
                    break;
                case 7:
                    try {
                        updateMovieListing();
                        System.out.println("Changes have been applied.\n"+
                                "Press ENTER to go back.");
                        readString();
                    } catch (IOException ex) {
                    	System.out.println("Failed to apply changes.\n"+
                                "Press ENTER to go back.");
                        readString();
                    }
                    break;
            }
        }
        displayMovieDetailMenu(movie);
    }

    /**
     * Removes a movie listing.
     * @param movie the movie to be removed.
     */
    private void removeListingMenu(Movie movie) {
        if (askConfirm("Are you sure to remove the listing?",
                "Enter Y to confirm, N to cancel:")) {
            try {
                removeMovieListing(movie);
                removeAllShowtime(movie);
                System.out.println("The listing has been removed.");
            } catch (IOException ex) {
                System.out.println("Failed to remove listing");
            }
        }

        displayMenu();
    }

}
