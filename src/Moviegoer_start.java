//starts moviegoer app by signup or login for moviegoer depending on if they are new or not
import java.util.Scanner;
import java.util.ArrayList;
public class Moviegoer_start {
	private Moviegoer_data moviegoer;
	private Scanner scan = new Scanner(System.in);
	public void start() {
		if (Moviegoer_isNew.isNew(scan)) {
			Moviegoer_signup signup = new Moviegoer_signup();
			moviegoer = signup.get_user(scan);
		}
		else {
			Moviegoer_login login = new Moviegoer_login();
			moviegoer = login.get_user(scan,null,null);
		}
		//to do: make this class single responsibility. trying to do open closed principle for menu class below
		ArrayList<Moviegoer_menuitem> menuitems = new ArrayList<Moviegoer_menuitem>();
		menuitems.add(new Moviegoer_booking(moviegoer));
		Moviegoer_menu menu = new Moviegoer_menu(menuitems);
		menu.show_menu(scan);
	}
}
