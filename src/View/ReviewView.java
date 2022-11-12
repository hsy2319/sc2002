package View;
import main.*;
import java.util.Scanner;

public class ReviewView extends View {
	private Movie movie;

	public ReviewView(Movie movie) {
		this.movie = movie;
	}
	@Override
	public void start(Scanner scan) {
		System.out.println("Review");
		System.out.printf(""
				+ "1. Write reviews\n"
				+ "2. View reviews\n"
				+ "3. Go back\n");
		
		int choice = readChoice(1, 3);
        switch (choice) {
            case 1:
                addReview();
                break;
            case 2:
                listReviews();
                break;
            case 3:
                destroy();
                break;
        }
        destroy();
	}
	
	public void addReview() {
		System.out.println("Write a Review");
		System.out.print("Your Name:");
		String name = scan.next();
		System.out.print("Enter rating between 1 to 5: ");
		int rating = readChoice(1,5);
		System.out.println("Enter your comments below:");
		String para = scan.next();
		Review review = new Review(this.movie,rating,para,name);
	}
	
	public void listReviews() {
		
	}

}
