package main;

import java.util.*;


//edits need to be made after combining to whole program
public class MovieShowTime {
	//define showtime
	//add manual timing
	//instantiate all timings to 0 for updating later on
	static int[] shaw = {0, 1330, 1500, 1730, 1800, 2000};
	static int[] cathay = {1100, 1330, 1500, 1730, 1800, 0};
	static int[] gv = {1100, 1330, 1500, 1730, 1800, 2000};
	//above needs to change to csv
	
	static int[] showtimes[] = {shaw, cathay, gv};
	
//	//define cineplex
//	static String[] ShawTheatre = {"Jurong East", "CCK", "Scotts Rd"};
//	static String[] CathayCineplex = {"Bugis", "Bukit Batok","Handy Rd"};
//	static String[] GoldenVillage = {"Bishan", "Jurong Pt", "Vivocity"};
//
//	static String[] Cineplex[] = {ShawTheatre, CathayCineplex, GoldenVillage};

	//remove after combining with whole program
	public static void main(String[] args) {
		displayShowtime();
	}
	
	public static void displayShowtime()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("1. Shaw Theatre: " + Arrays.toString(shaw));
		System.out.println("2. Cathay Cineplex: " + Arrays.toString(cathay));
		System.out.println("3. Golden Village: " + Arrays.toString(gv));
        System.out.println("Select cinema to edit ('-2' to go back): ");
        
        int selection = input.nextInt();
        
        if (selection == -2){
            return; //go back to previous page
        }
        
        if (selection > 0 && selection < 4)
        {
        	String name;
        	
        	if (selection == 1)
        	{
        		name = "Shaw";
        		updateCinema(name, showtimes[0]);
        	}
        	if (selection == 2)
        	{
        		name = "Cathay";
        		updateCinema(name, showtimes[1]);
        	}
        	if (selection == 3)
        	{
        		name = "GV";
        		updateCinema(name, showtimes[2]);
        	}
        }        
	}
	
	
	//methods for CRUD (Create, Read, Update, Delete)
	public static void updateCinema(String name, int[] showtimes)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println(name + " showtimes: " + Arrays.toString(showtimes));
    	System.out.println("1. Add new showtime");
    	System.out.println("2. Edit current showtimes");
    	System.out.println("3. Delete showtime");
    	System.out.println("Select option ('-2' to go back): ");
    	
    	int choice = sc.nextInt();
    	
    	if (choice == -2){
            displayShowtime();
        }
    	
    	switch(choice)
        {
        	case 1:
        		addTime(showtimes);
        		displayShowtime();
        		break;
        	case 2:
        		editTime(showtimes);
        		displayShowtime();
        		break;
        	case 3:
        		deleteTime(showtimes);
        		displayShowtime();
        		break;
        }
	}
	
	
	//admin chooses to add new timeslot
	public static void addTime(int[] showtimes)
	{
		Scanner sc = new Scanner(System.in);
		
		int i;
		
		for (i = 0; i < showtimes.length; i++)
		{
			if (showtimes[i] == 0)
			{
				System.out.println("Enter Time between 1100 to 2300 ('-2' to exit): ");
				int time = sc.nextInt();
				
				if (time == -2){
		            displayShowtime();
		        }
				else
				{
					showtimes[i] = time;
					System.out.println("Timeslot added.");
					break;
				}
			}
		}
		
		if (i == showtimes.length)
		{
			System.out.println("There are no available slots to be added.");
			return;
		}
	}
	
	public static void editTime(int[] showtimes)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select timeslot 1 to 6 to edit ('-2' to exit): " + showtimes);
		int index = sc.nextInt();
		
		if (index == -2){
			return;
        }
		
		for (int i = 0; i < showtimes.length; i++)
		{
			if (i == index-1)
			{
				System.out.println("Enter a new showtime: ");
				int newTime = sc.nextInt();
				
				showtimes[i] = newTime;
				System.out.println("Timeslot updated.");
			}
		}
	}
	
	public static void deleteTime(int[] showtimes)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select timeslot 1 to 6 to delete ('-2' to exit): " + showtimes);
		int index = sc.nextInt();
		
		if (index == -2){
			return;
        }
		
		for (int i = 0; i < showtimes.length; i++)
		{
			if (i == index-1)
			{
				showtimes[i] = 0;
				System.out.println("Timeslot deleted.");
			}
		}
	}
	
	//public static void 
}


