//log in moviegoer
import java.io.*;
import java.util.Scanner;
public class Moviegoer_login {
	public Moviegoer_data get_user(Scanner scan, String name, String password) {
		String email=null, mobile=null;
		try {
			System.out.println("Logging in.");
			while (email==null) {
				if (name==null) {
					System.out.print("Name: ");
					name = scan.nextLine();
				}
				if (password==null) {
					System.out.print("Password: ");
					password = scan.nextLine();
				}
				String moviegoer_entry = null;
				BufferedReader reader = new BufferedReader(new FileReader("src/Data/Moviegoer.csv"));
				reader.readLine();
				while ((moviegoer_entry = reader.readLine())!=null) {
					String nameInFile = moviegoer_entry.split(",")[0];
					String passwordInFile = moviegoer_entry.split(",")[3];
					if (name.equals(nameInFile)&&password.equals(passwordInFile)) {
						System.out.println("Logged in.");
						reader.close();
						mobile = moviegoer_entry.split(",")[1];
						email = moviegoer_entry.split(",")[2];
						break;
					}
				}
				reader.close();
				if (email==null) {
					name = null;
					password = null;
					System.out.print("Wrong name or password.\n1.Quit\nAny other key. Try again\nChoice:");
					if (scan.nextLine().equals("1")) System.exit(0);
				}
			}
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
		return new Moviegoer_data(name,mobile,email,password);
	}
}
