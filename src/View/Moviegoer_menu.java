package View;
//show possible actions to moviegoer
import java.util.ArrayList;
import java.util.Scanner;

import main.Moviegoer_menuitem;
public class Moviegoer_menu {
	private ArrayList<Moviegoer_menuitem> items;
	public Moviegoer_menu(ArrayList<Moviegoer_menuitem> items) {
		this.items = items;
	}
	public void show_menu(Scanner scan) {
		int choice;
		while (true) {
			System.out.println("What would you like to do?");
			for (int i=0;i<items.size();i++) {
				System.out.println((i+1)+". "+items.get(i).getDescription());
			}
			System.out.println((items.size()+1)+". Exit.");
			System.out.print("Choice: ");
			choice = Integer.parseInt(scan.nextLine());
			if (choice == items.size()+1) {
				System.out.println("Program exited.");
				break;
			}
			else {
				items.get(choice-1).action(scan);
			}
		}
	}
}
