package View;

import static Controller.IOController.*;
import static Controller.Manager.*;

import View.staff.MovieListing;
import View.staff.SystemSetting;

/**
 * The view shown to staff.
 *
 * @version 1.0
 */
public class StaffView extends View {
    private boolean loggedIn;

    public StaffView() {
        loggedIn = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        if (!loggedIn) login();
        else displayMenu();
    }

    /**
     * Gets user to login
     */
    private void login() {
        System.out.println("Please login.");

        String username = readString("Username: ");
        String password = readString("Password: ");

        if (authentication(username, password)) {
            loggedIn = true;
            System.out.println("Login successful.");
            displayMenu();
        }
        else {
            System.out.println("Invalid username or password.");
            destroy();
        }
    }

    private void displayMenu() {
    	System.out.println("Staff");
    	System.out.println("Please choose:\n"+
                "1. Modify movie listing\n"+
                "2. Configure system settings\n"+
                "3. Logout");

        int choice = readChoice(1, 3);

        switch (choice) {
            case 1:
                intent(this, new MovieListing());
                break;
            case 2:
                intent(this, new SystemSetting());
                break;
            case 3:
                loggedIn = false;
                System.out.println("Logout successful.");
                destroy();
                break;
        }
    }

}
