package View;
import main.*;
import java.util.*;
import java.text.*;

import main.Holiday;
public class SystemSettingsView extends View{
	private boolean rankbyTix = true;
	
	public boolean getRankbyTix(){return rankbyTix;}
	
	public void start(Scanner scan) {
		System.out.printf(""
				+ "1. Cinema/Cineplex settings (Add/remove showtimes here) \n"
				+ "2. Configure holidays\n"
				+ "3. Top 5 ranking movies settings\n"
				+ "4. Configure Ticket prices \n"
				+ "5. Go back \n");

		int choice = readChoice(1,4);
		;
		
		switch(choice) {
		case 1:
			//Cinema/Cineplex settings
			branch(this, new CineSettingsView());
			break;
		case 2:
			//Holiday settings
			holidaysettings();
			break;
		case 3:
			//Top 5 ranking settings
			topfiveranking();
			break;
		case 4:
			//configure ticket prices
			break;
		case 5:
			// go back
			destroy();
			break;
		}
	}
	
	private void topfiveranking() {
		System.out.println("Top 5 movie ranking preference:");
		System.out.printf(""
			+ "1. Rank by number of ticket sales\n"
			+ "2. Rank by average rating\n"
			+ "3. Go back \n");
			int choice = readChoice(1,3);
			if(choice == 1) {
				rankbyTix = true;
				//do the ranking
				System.out.println("Movies will be ranked by ticket sales");
			}
			else if (choice == 2) {
				rankbyTix = false;
				//do the ranking
				System.out.println("Movies will be ranked by average rating");
			}
			else if (choice == 3) destroy();
	}
	
	private void holidaysettings() {
		System.out.printf(""
				+ "1. List the holidays \n"
				+ "2. Add a holiday \n"
				+ "3. Go back \n");
		int choice = readChoice(1,3);
		;
		switch(choice) {
		case 1:
			listHolidays();
			break;
		case 2:
			addHoliday();
			break;
		case 3:
			destroy();
			break;
		}
	}
	private void listHolidays() {
		System.out.println("List of Holidays");
		//List the holidays
		System.out.printf("");
	}
	
	private void addHoliday() {
		String name;
		Date date;
		double discount;
		
		System.out.println("Enter the name of holiday: ");
		name = scan.next();
		System.out.println("Enter the date of holiday in the format: MM-DD (e.g. 04-25) ");
		String input = scan.next();
		date = readDate(input);
		System.out.println("Enter the discounted ticket price on that day,\n"
				+ "(e.g. 0.5 means ticket is half priced)");
		discount = scan.nextDouble();
		
		Holiday holiday = new Holiday(name, date, discount);
		System.out.println(holiday);//testing
		//add holiday to list of holidays
	}
	
	private void deleteHoliday() {
		
	}
	
	public Date readDate(String input){
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				input = new SimpleDateFormat("yyyy").format(new Date()) + "-" + input;
				Date date = simpledateformat.parse(input);
				return date;
			}catch(ParseException ex) {
				System.out.println("Invalid input. Try again.");
				return readDate(input);
			}
		}
}





