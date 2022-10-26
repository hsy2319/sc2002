//sign up moviegoer
import java.io.*;
import java.util.Scanner;
public class Moviegoer_signup extends Moviegoer_login{
	public Moviegoer_data get_user(Scanner scan) {
		String name = null, password=null;
		try {
			System.out.println("Creating new account:");
			boolean isNameInFile;
			do {
				isNameInFile = false;
				System.out.print("Name: ");
				name = scan.nextLine();
				String moviegoer_entry = null;
				BufferedReader reader = new BufferedReader(new FileReader("src/Data/Moviegoer.csv"));
				reader.readLine();
				while ((moviegoer_entry = reader.readLine())!=null) {
					String nameInFile = moviegoer_entry.split(",")[0];
					if (name.equals(nameInFile)) {
						isNameInFile = true;
						System.out.println("Error: name is already in file. Please enter another name.");
						break;
					}
				}
				reader.close();
			} while (isNameInFile);
			System.out.print("Mobile number: ");
			String mobile_no = scan.nextLine();
			System.out.print("Email: ");
			String email = scan.nextLine();
			System.out.print("Password: ");
			password = scan.nextLine();
			BufferedWriter writer = new BufferedWriter(new FileWriter("src/Data/Moviegoer.csv",true));
			writer.newLine();
			writer.write(name+","+mobile_no+","+email+","+password);
			writer.close();
			System.out.println("New account created.");
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
		return super.get_user(scan, name, password);
	}
}
