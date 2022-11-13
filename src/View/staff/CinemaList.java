package View.staff;

import java.io.IOException;
import java.util.ArrayList;

import Controller.Manager;
import Model.Cinema;
import View.View;

import static Controller.IOController.*;
import static Controller.Manager.*;
import static Model.Constant.*;

/**
 * Shows the cinema list view.
 *
 * @version 1.0
 */
public class CinemaList extends View {
    private boolean help;

    CinemaList(String args) {
        help = args.equals("help");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (help) displayCinemaListMenu();
        else displayMenu();
    }

    /**
     * Shows the main menu of cinema list.
     */
    private void displayMenu() {
    	System.out.println("Configure cinemas");
    	System.out.println("1. List cinemas\n"+
                "2. Add cinemas\n"+
                "3. Go back");

        int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                displayCinemaListMenu();
                break;
            case 2:
                addCinema();
                break;
            case 3:
                break;
        }
        destroy();
    }

    /**
     * Shows a list of cineplex.
     */
    private void displayCinemaListMenu() {
        int index = 0;
        for (Cineplex c : Cineplex.values()) {
            index++;
            System.out.println(index + ": " + c);
        }

        System.out.println("Choose a cineplex to view cinema codes:");
        int choice = readChoice(1, index);
        displayCinemaList(Cineplex.values()[choice - 1]);
    }

    /**
     * Shows the cinema list specified by cineplex.
     * @param cineplex the cineplex of the cinema
     */
    private void displayCinemaList(Cineplex cineplex) {
    	System.out.println(cineplex.toString()+"\n");
        ArrayList<Cinema> cinemaList = getCinemaList(cineplex);

        if (cinemaList == null) {
        	System.out.println("No cinema found.\n"+
                    "Press ENTER to go back");
            readString();
        }
        else {
            for (Cinema cinema : getCinemaList(cineplex)) {
                System.out.print(cinema + "(" + (cinema.is3D() ? "3D" : "Digital") + ") ");
            }
            System.out.println();
        }

        if (help) destroy();
        else displayMenu();
    }

    /**
     * Adds a cinema.
     */
    private void addCinema() {
        int index = 0;
        for (Cineplex c : Cineplex.values()) {
            index++;
            System.out.println(index + ": " + c);
        }

        System.out.println("Choose a cineplex to add the cinema:");
        int choice = readChoice(1, index);
        Cineplex cineplex = Cineplex.values()[choice - 1];

        boolean isPlatinum = askConfirm("Is this a platinum cinema?",
                "Enter Y for yes, N for no:");
        
        boolean is3D = askConfirm("Is this cinema for 3D movies?",
                "Enter Y for yes, N for no");
        
        double basePrice = readDouble("What's the base price for the cinema?",
                "(Weekday price = base price * 1.2, senior citizens enjoy 50% off)");
        
        String code = readString("Enter the cinema code",
                "e.g. ABC (do not enter the same cinema code for two different cinemas)");

        Cinema cinema = new Cinema(cineplex, isPlatinum, is3D, code, basePrice);
        try {
            Manager.addCinema(cinema);
            System.out.println("Successfully added the cinema.");
        } catch (IOException ex) {
            System.out.println("Failed to add the cinema.");
        } finally {
            destroy();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void destroy() {
        if (getPrevView().getClass() == ShowtimeView.class) ((ShowtimeView)getPrevView()).addShowtime();
        else super.destroy();
    }
}
