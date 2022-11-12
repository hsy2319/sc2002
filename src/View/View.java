package View;
import java.util.InputMismatchException;
import java.util.Scanner;
public abstract class View {
	
	//to start off all views (list of all choices)
	Scanner scan = new Scanner(System.in);
	public abstract void start(Scanner scan);
	public View previousView;
	
	//going back
	protected void destroy() {
        if (previousView == null) System.exit(1);
        else previousView.start(scan);
    }
	
	//change to another view (branch into other views)
	protected void branch(View current, View branch) {
        branch.previousView = current;
        branch.start(scan);
    }
	
	public static int readChoice(int lower, int upper) {
        Scanner scan = new Scanner(System.in);
        int choice;
        
        //invalid input
        try {
            choice = scan.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            scan.nextLine();
            return readChoice(lower, upper);
        }
        
        //out of bounds
        if (choice < lower || choice > upper) {
            System.out.println("Invalid input, try again.");
            return readChoice(lower, upper);
        }
        return choice;
    }
	
}