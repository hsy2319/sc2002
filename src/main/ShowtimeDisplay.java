/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Essh
 */
import java.util.*;
public class ShowtimeDisplay {
    public static List<Cinema> getAllShowtimes(){
        return Database.loadShowTimes(-1);

        /**
         * Get all showtimes in the database which corresponds to input movie ID
         * @param movieID movie ID2
         * 
         * @return A list of Cinema objects
         */
    }
    public static List<Cinema> getMovieShowtimes(int movieID){
        return Database.loadShowTimes(movieID);

    }
}
