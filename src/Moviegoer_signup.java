//sign up moviegoer
import java.util.Scanner;
public class Moviegoer_signup extends Moviegoer_login{
	public Moviegoer_data get_user(Scanner scan) {
		String name, password;
		boolean existsInFile;
		System.out.println("Creating new account:");
		do {
			existsInFile = false;
			System.out.print("Name: ");
			name = scan.nextLine();
			existsInFile = !(Moviegoer_Database.getMoviegoer(name)==null);
			if (existsInFile)
				System.out.println("Name already in file. Please try again.");
			} while (existsInFile);
		System.out.print("Mobile number: ");
		String mobile_no = scan.nextLine();
		System.out.print("Email: ");
		String email = scan.nextLine();
		System.out.print("Password: ");
		password = scan.nextLine();
		Moviegoer_Database.setMoviegoer(new Moviegoer_data(name,mobile_no,email,password));
		System.out.println("New account created.");
		return super.get_user(scan, name, password);
	}
}