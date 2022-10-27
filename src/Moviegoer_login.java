//log in moviegoer
import java.util.Scanner;
public class Moviegoer_login {
	public Moviegoer_data get_user(Scanner scan, String name, String password) {
		boolean existsInFile;
		System.out.println("Logging in.");
		do {
			if (name==null) {
				System.out.print("Name: ");
				name = scan.nextLine();
			}
			if (password==null) {
				System.out.print("Password: ");
				password = scan.nextLine();
			}
			existsInFile = !(Moviegoer_Database.getMoviegoer(name,password)==null);
			if (!existsInFile) {
				System.out.print("Wrong name or password.\n1.Quit\nAny other key. Try again\nChoice:");
				if (scan.nextLine().equals("1")) {
					System.out.println("Program exited.");
					System.exit(0);
				}
				name=null;
				password=null;
			}
		} while (!existsInFile);
		return Moviegoer_Database.getMoviegoer(name,password);
	}
}
