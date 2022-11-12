package View;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.*;

import main.Movie;
import main.MovieDisplay;

public class Boundary {

    private static final String[] AdminChoice={"Create movie listing","Update movie listing","Other System Settings"};

    private static final String[] startMenu ={"Make modifications"};

    private static final String[] movieOptions={"movie name","language","runtime","Cast member","Synopsis","director","Status"};

    public static int moduleSelection(int choice, Scanner input){
        while(choice != 1 && choice !=0){
            System.out.println("Welcome Admin!");
            System.out.println("MOBLIMA Movie Booking System ");
            Boundary.displayOptions("startMenu");

            try{
                choice = input.nextInt();
                if(choice == 0){
                    System.exit(0);
                }
                else if(choice ==1){

                    return choice;
                }
                else{
                    System.out.println("Error! Please enter either 1, 2 or 0:");
                }
            }
            catch(InputMismatchException e){
                System.out.println("That is not an integer, please try again." );
                input.next();
            }


        }

        return 0;
    }

    public static void displayOptions(String input) {

        String str[]={};

        switch (input){
            case "staffMenu":
                str=AdminChoice;
                break;
            case "startMenu":
                str= startMenu;
                break;
            case "moviesMenu":
                str=movieOptions;
                break;
        }
        int count = 1;
        for (int i = 0; i < str.length; i++)
            System.out.println(" " + count++ + " : " + str[i]);

        if(input.equals("moviesMenu"))
            System.out.println(" "+0+" : "+"Done");
        else
            System.out.println(" "+0+" : "+"Exit");

        System.out.println();
    }

    
    public static void displayMovie(List<Movie> movieList){
        System.out.format("%-5s %-25s %-15s %-15s %n", "ID", "Title", "Rating(Avg)", "Language");
        for(Movie movie:movieList){
            String rating;
            if (MovieDisplay.getAvgRating(movie) == -1)
                rating="NA";
            else
                rating=String.format("%.3g",MovieDisplay.getAvgRating(movie));

            System.out.format("%-5d %-25s %-15s %-15s %n", movie.getId(), movie.getName(),rating, movie.getLang());

        }
        System.out.println();
    }

    
    public static void displayMovie(Movie movie){

        System.out.format("%-10s   %-20s   %-7s   %-10s  %-15s   %-10s         %-80s  %-30s  %n","Movie ID","Movie Name","Rating",
                "Language","Status","Director","Cast","Synopsis");

        String rating;
        if(MovieDisplay.getAvgRating(movie) == -1)
            rating="NA";
        else
            rating=String.format("%.3g",MovieDisplay.getAvgRating(movie));



        System.out.format("%-10s   %-20s   %-7s   %-10s  %-15s   %-15s  %-80s  %-30s  %n",movie.getId(),movie.getName(),rating,movie.getLang(),movie.getStatus(),movie.getDirector(),movie.getCast(),movie.getSynopsis());
        System.out.println();
    }

    public static void displayMovieReviews(Movie movie){
        int reviewNum = movie.getRating().size() < movie.getReviews().size() ? movie.getRating().size() : movie.getReviews().size();
        List<Integer> rating_list = movie.getRating();
        List<String> review_list = movie.getReviews();

        System.out.format("%-7s %-20s %n","Rating","Review");

        for(int i = 0; i < reviewNum; i++)
            System.out.format("%-1s/5    %-20s  %n",rating_list.get(i),review_list.get(i));
        System.out.println();

    }

}
