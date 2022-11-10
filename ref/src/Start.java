import Controller.Manager;
import View.*;

import static Controller.IOController.*;

/**
 * Starts the application.
 *
 * @version 1.0
 */
public class Start extends View {
    /**
     * {@inheritDoc}
     */
    @Override
    protected void start() {
        // initialize Manager
        boolean initialized = Manager.initialize();
        if (!initialized) {
            System.out.println("Error: failed to read data.");
            System.out.println("Application exited");
            return;
        }

        printTitle("Movie Booking and Listing Management Application");
        printChoices("Please choose moviegoer or staff:",
                "1. moviegoer",
                "2. staff",
                "3. Exit application","");

        int choice = readChoice(1, 3);

        switch(choice) {
            case 1:
                intent(this, new MoviegoerView());
                break;
            case 2:
                intent(this, new StaffView());
                break;
            case 3:
                System.out.println("Application exited.");
                destroy();
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
        }
    }

    /**
     * The main function.
     * @param args unused
     */
    public static void main(String[] args) {
        new Start().start();
    }
}
