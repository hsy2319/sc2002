package View;

import java.util.Scanner;
public class MoviegoerView extends View {
	public void start(Scanner scan) {
		System.out.println("Moviegoer");
		System.out.printf(""
				+ "1.Search or list movies\n"
				+ "2.Book a movie ticket\n"
				+ "3. Go back \n");
		int choice= readChoice(1,3);
		switch(choice) {
		case 1:
			branch(this, new MovieDisplayView());
			break;
		case 2:
			branch(this,new Moviegoer_booking());
			break;
		case 3:
			destroy();
			break;
		}
		
	}
}