//check if moviegoer has account
import java.util.Scanner;

public class Moviegoer_isNew {
	public static boolean isNew(Scanner scan) {
		System.out.println("Are you a new user?");
		System.out.println("1. yes: create a new Moviegoer account.");
		System.out.println("2. no: log in to existing account.");
		System.out.print("Choice: ");
		String input = scan.nextLine();
		while (!input.equals("1")&&!input.equals("2")) {
			System.out.println("Please type either 1 or 2.");
			input = scan.nextLine();
		}
		if (input.equals("1")) return true;
		return false;
	}
}
