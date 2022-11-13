package View.staff;

import static Controller.IOController.*;
import static Controller.Manager.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Controller.Manager;
import Model.*;
import View.View;

/**
 * Shows the showtime view.
 *
 * @version 1.0
 */
public class ShowtimeView extends View {
    private Movie movie;

    ShowtimeView(Movie movie) {
        this.movie = movie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Shows the main menu of showtime.
     */
    private void displayMenu() {
    	System.out.println("Show time\n");
        ArrayList<Model.Showtime> showtimeList = getMovieShowtime(movie);
        if (showtimeList == null || showtimeList.isEmpty()) {
        	System.out.println("No showtime on that day.\n"+
                    "1. Add a show time\n"+
                    "2. Go back\n");
            int choice = readChoice(1, 2);
            if (choice == 1) addShowtime();
            else destroy();
        }
        else {
            showtimeList.sort(Comparator.comparing(o -> o.getCinema().getCineplex().toString()));

            int index = 0;
            for (Model.Showtime s : showtimeList) System.out.println(++index + ": " + s);

            System.out.println((index + 1) + ". Go back\n"+
                    "Please choose a showtime.\n"+
                    "To add a showtime, enter 0:");

            int choice = readChoice(0, index + 1);
            if (choice == 0) addShowtime();
            else if (choice == index + 1) destroy();
            else {
                Showtime showtime = showtimeList.get(choice - 1);
                displayShowtimeDetailMenu(showtime);
            }
        }
    }

    /**
     * Shows the menu of showtime detail
     * @param showtime the showtime whose detail to be displayed
     */
    private void displayShowtimeDetailMenu(Showtime showtime) {
    	System.out.println(showtime.toString()+"\n");
    	System.out.println(showtime.getDetails()+"\n"+
                "1. Modify cineplex/cinema\n"+
                "2. Modify time\n"+
                "3. Remove showtime\n"+
                "4. Go back");

        int choice = readChoice(1, 4);
        switch (choice) {
            case 1:
                modifyCinema(showtime);
                break;
            case 2:
                modifyTime(showtime);
                break;
            case 3:
                if (askConfirm("Are you sure to remove the showtime?",
                        "Enter Y to confirm, N to cancel:")) {
                    try {
                        Manager.removeShowtime(showtime);
                        System.out.println("The showtime has been removed.");
                    } catch (IOException ex) {
                        System.out.println("Failed to remove showtime");
                    }
                    displayMenu();
                }
            case 4:
                displayMenu();
                break;
        }
    }

    /**
     * Modifies the cinema of the showtime.
     * @param showtime the showtime to be modified
     */
    private void modifyCinema(Showtime showtime) {
        Cinema cinema;
        String input = readString("Enter cinema code:");
        cinema = getCinemaByCode(input);

        if (cinema == null) {
        	System.out.println("Invalid cinema code.\n"+
                    "Press ENTER to go back.");
            readString();
            displayShowtimeDetailMenu(showtime);
        }
        else {
            try {
                showtime.setCinema(cinema);
                updateShowtime();
                System.out.println("Successfully modified cinema.");
            } catch (IOException ex) {
                System.out.println("Failed to modify cinema.");
            }
            finally {
                displayShowtimeDetailMenu(showtime);
            }
        }
    }

    /**
     * Modifies the time of the showtime.
     * @param showtime the showtime to be modified
     */
    private void modifyTime(Showtime showtime) {
    	System.out.println("Modify time\n");
        Date time = readTimeMMddkkmm("Enter the time for the show",
                "Format: MM-DD HH:MM (e.g. 12-25 09:30)");

        showtime.setTime(time);

        try {
            showtime.setTime(time);
            updateShowtime();
            System.out.println("Successfully modified time.");
        } catch (IOException ex) {
            System.out.println("Failed to modify time.");
        }
        finally {
            displayShowtimeDetailMenu(showtime);
        }
    }

    /**
     * Adds a showtime.
     */
    void addShowtime() {
        Cinema cinema;

        System.out.println("Add showtime\n");
        String input = readString("Enter cinema code (enter \"help\" to look up cinema code)");
        if (input.equals("help")) {
            intent(this, new CinemaList("help"));
            displayMenu();
            return;
        }
        else cinema = getCinemaByCode(input);

        if (cinema == null) {
        	System.out.println("Invalid cinema code.\n"+
                    "Press ENTER to go back.");
            readString();
            displayMenu();
        }
        Date time = readTimeMMddkkmm("Enter the time for the show",
                "Format: MM-DD HH:MM (e.g. 12-25 09:30)");
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setCinema(cinema);
        showtime.setTime(time);

        try {
            Manager.addShowtime(showtime);
            System.out.println("Successfully added showtime.");
        } catch (IOException ex) {
            System.out.println("Failed to add showtime.");
        }
        finally {
            displayMenu();
        }
    }
}
