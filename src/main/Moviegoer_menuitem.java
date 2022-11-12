package main;
//abstract class for each menu item
import java.util.Scanner;
public abstract class Moviegoer_menuitem {
	private String description;
	abstract void action(Scanner scan);
	public Moviegoer_menuitem(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
