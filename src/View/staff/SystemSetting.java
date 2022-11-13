package View.staff;

import static Controller.IOController.*;
import static Controller.Manager.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import Controller.Manager;
import Model.Cinema;
import Model.Holiday;
import View.View;

/**
 * Shows the system setting view.
 *
 * @version 1.0
 */
public class SystemSetting extends View{
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        displayMenu();
    }

    /**
     * Shows the main menu of system setting.
     */
    private void displayMenu() {
    	System.out.println("System setting\n");
    	System.out.println("1. Configure ticket prices of cinemas\n"+
                "2. Configure top 5 ranking schema\n"+
                "3. Configure cinemas\n"+
                "4. Configure holidays\n"+
                "5. Go back"
                );

        int choice = readChoice(1, 5);
        switch (choice) {
            case 1:
                configureTicket();
                break;
            case 2:
                configureTop5Ranking();
                break;
            case 3:
                intent(this, new CinemaList("configure"));
                break;
            case 4:
                configureHolidays();
                break;
            case 5:
                destroy();
                break;
        }
    }

    /**
     * Configures the scheme of top 5 ranking of movies.
     */
    private void configureTop5Ranking() {
    	System.out.println("Configure top 5 ranking scheme\n");
        boolean movieOrder = getSystem().get("movieOrder");

        if (askConfirm("Current top 5 ranking is ordered by " + (movieOrder ? "overall rating" : "sales") + ",",
                "Change to order by " + (!movieOrder ? "overall rating" : "sales") + "?",
                "Enter Y to confirm, N to cancel:")) {
            try {
                getSystem().put("movieOrder", !movieOrder);
                System.out.println("Successfully changed the setting.");
                System.out.println();
                updateSystem();
            } catch (IOException ex) {
                System.out.println("Failed to change the setting.");
                System.out.println();
            }
        }

        displayMenu();
    }

    /**
     * Configures the base ticket price of cinemas.
     */
    private void configureTicket() {
    	System.out.println("Configure ticket prices of cinemas\n");
        Cinema cinema = null;
        while (cinema == null) {
            String input = readString("Enter cinema code (enter \"help\" to look up cinema code)");
            if (input.equals("help")) {
                intent(this, new CinemaList("help"));
                displayMenu();
                return;
            } else {
                cinema = getCinemaByCode(input);
                if (cinema == null) System.out.println("Cinema code is invalid. Try again:");
            }
            System.out.println();
        }

        System.out.println(cinema.isPlatinum() ? cinema.getCode() + " (Platinum)" : cinema.getCode()+"\n");
        if (askConfirm("The ticket price of the cinema is " + cinema.getBasePrice() + ".",
                "Proceed to change?",
                "Enter Y to confirm, N to cancel:")) {
            double newPrice = readDouble("Enter the new ticket price:");

            cinema.setBasePrice(newPrice);
            try {
                updateCinemaList();
                System.out.println("Ticket price has been successfully changed.");
            } catch (IOException ex) {
                System.out.println("Failed to change ticket price.");
            }
        }

        displayMenu();
    }

    /**
     * Shows the menu of configuring holidays.
     */
    private void configureHolidays() {
    	System.out.println("Configure holidays\n");
    	System.out.println("1. List all holidays\n"+
                "2. Add a holiday\n"+
                "3. Go back\n");
        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                displayHolidayList();
                break;
            case 2:
                addHoliday();
                break;
            case 3:
                break;
        }

        displayMenu();
    }

    /**
     * Shows the list of holidays.
     */
    private void displayHolidayList() {
    	System.out.println("Holiday list\n");
        HashMap<String, Holiday> holidayList = getHolidayList();
        HashMap<Integer, Holiday> searchIndex = new HashMap<>();
        if (holidayList.isEmpty()) {
        	System.out.println("No holiday exists\n");
            readString("Press ENTER to go back");
            configureHolidays();
        }
        else {
            int index  = 0;
            for (String date : holidayList.keySet()) {
                System.out.println(++index + ". " + holidayList.get(date));
                searchIndex.put(index, holidayList.get(date));
            }
            System.out.println(++index + ". Go back");
            System.out.println();

            int choice = readChoice(1, index);
            if (choice == index) configureHolidays();
            else displayHolidayDetail(searchIndex.get(choice));
        }
    }

    /**
     * Shows detail of the holiday and allows user
     * to remove the holiday.
     * @param holiday the holiday whose detail to be displayed
     */
    private void displayHolidayDetail(Holiday holiday) {
    	System.out.println(holiday.getName());
    	System.out.println(holiday.printDetail()+"\n");
        if (askConfirm("Enter Y to delete the holiday",
                "Enter N to go back:")) {
            getHolidayList().remove(formatTimeMMdd(holiday.getDate()));
            try {
                updateHolidayList();
                System.out.println("Successfully deleted the holiday.");
            } catch (IOException e) {
                System.out.println("Failed to delete the holiday.");
            }
        }
        displayHolidayList();
    }

    /**
     * Adds a holiday.
     */
    private void addHoliday() {
        String name;
        Date date;
        double discount;

        name = readString("Enter the name of the holiday:").toLowerCase();
        date = readTimeMMdd("Enter the date of the holiday",
                "Format: MM-DD (e.g. 12-25)");
        discount = readDouble("Enter the price rate on that day:",
                "e.g. 0.7 stands for ticket price * 0.7");

        Holiday holiday = new Holiday(name, date, discount);

        try {
            Manager.addHoliday(formatTimeMMdd(date), holiday);
            System.out.println("Successfully added the holiday.");
        } catch (IOException ex) {
            System.out.println("Failed to add the holiday.");
        }

        displayHolidayList();
    }
}
