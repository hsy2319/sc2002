package View;

import View.staff.MovieListing;
import View.staff.SystemSetting;

import static Controller.Manager.*;
import static Controller.IOController.*;

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
        printTitle("Staff");
        printChoices("Please choose:",
                "1. Modify movie listing",
                "2. Configure system settings",
                "3. Logout", "");

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
