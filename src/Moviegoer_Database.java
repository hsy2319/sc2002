import java.io.*;
public class Moviegoer_Database {
	private static final File file = new File("src/Data/Moviegoer.csv");
	public static Moviegoer_data getMoviegoer(String name, String password) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String moviegoer_entry;
			reader.readLine();
			while ((moviegoer_entry = reader.readLine())!=null) {
				String nameInFile = moviegoer_entry.split(",")[0];
				String passwordInFile = moviegoer_entry.split(",")[3];
				if (name.equals(nameInFile)&&password.equals(passwordInFile)) {
					reader.close();
					String mobile = moviegoer_entry.split(",")[1];
					String email = moviegoer_entry.split(",")[2];
					return new Moviegoer_data(name,mobile,email,password);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Moviegoer_data getMoviegoer(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String moviegoer_entry;
			reader.readLine();
			while ((moviegoer_entry = reader.readLine())!=null) {
				String nameInFile = moviegoer_entry.split(",")[0];
				if (name.equals(nameInFile)) {
					reader.close();
					String mobile = moviegoer_entry.split(",")[1];
					String email = moviegoer_entry.split(",")[2];
					String password = moviegoer_entry.split(",")[3];
					return new Moviegoer_data(name,mobile,email,password);
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void setMoviegoer(Moviegoer_data moviegoer) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true));
			writer.newLine();
			writer.write(moviegoer.getName()+","+moviegoer.getMobile()+","+moviegoer.getEmail()+","+moviegoer.getPassword());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
