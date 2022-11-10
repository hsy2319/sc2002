package Controller;

import Model.*;

import java.io.IOException;
import java.util.*;

import static Model.Constant.*;
import static Controller.IOController.*;

/**
 * Reads and updates data files.
 *
 * @version 1.0
 */
public class Manager extends DataIO {
    /** file locations */
    private static final String FILENAME_MOVIE = "res/data/movieListing.dat";  // location of movie.dat
    private static final String FILENAME_SHOWTIME = "res/data/showtime.dat";  // location of showtime.dat
    private static final String FILENAME_STAFFACCOUNT = "res/data/staffAccount.dat";  // location of staffAccount.dat
    private static final String FILENAME_CINEMALIST = "res/data/cinemaList.dat";  // location of cinemaList.dat
    private static final String FILENAME_REVIEWLIST = "res/data/reviewList.dat"; //location of reviewList.dat
    private static final String FILENAME_BOOKINGHISTORY = "res/data/bookingHistory.dat";  // location of cinema.dat
    private static final String FILENAME_HOLIDAY = "res/data/holidayList.dat";  // location of holiday.dat
    private static final String FILENAME_SYSTEM = "res/data/system.dat"; // location of system.dat

    private static ArrayList<Movie> movieListing;
    private static HashMap<Movie, ArrayList<Showtime>> movieShowtime;
    private static HashMap<Cineplex, ArrayList<Cinema>> cinemaList;
    private static HashMap<String, String> staffAccount;
    private static ArrayList<BookingHistory> bookingHistory;
    private static HashMap<Movie, ArrayList<Review>> reviewList;
    private static HashMap<String, Holiday> holidayList;
    private static HashMap<String, Boolean> system;

    private Manager() { }

    /**
     * reads data from files
     * @return true if no error, else false
     */
    public static boolean initialize() {
        try {
            readSystem();
            readStaffAccount();
            readCinemaList();
            readMovieListing();
            readMovieShowtime();
            readReviewList();
            readHolidayList();
            readBookingHistory();
        } catch (IOException ex) {
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }

        return true;
    }

    /**
     * Reads movie listing (an {@code ArrayList<Movie>})
     * @throws IOException when file is not found
     * @throws ClassNotFoundException when class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieListing() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_MOVIE) == null) movieListing = new ArrayList<>();
        else {
            movieListing = (ArrayList<Movie>) readSerializedObject(FILENAME_MOVIE);
            // sort listing by movie status
            Collections.sort(movieListing, Comparator.comparing(o -> o.getMovieStatus().toString()));
        }
    }

    /**
     * Reads movie showtime (a {@code HashMap<Movie, Arraylist<Showtime>>})
     * @throws IOException when file is not found
     * @throws ClassNotFoundException when class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readMovieShowtime() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_SHOWTIME) == null) movieShowtime = new HashMap<>();
        else movieShowtime = (HashMap<Movie, ArrayList<Showtime>>) readSerializedObject(FILENAME_SHOWTIME);
    }

    /**
     * Reads staff account (a {@code HashMap<String, String>})
     * @throws IOException when file is not found
     * @throws ClassNotFoundException when class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readStaffAccount() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_STAFFACCOUNT) == null) staffAccount = new HashMap<>();
        else staffAccount = (HashMap<String, String>) readSerializedObject(FILENAME_STAFFACCOUNT);
    }

    /**
     * This method is to read cinema list (a {@code HashMap<Cineplex, ArrayList<Cinema>>}) and store it.
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readCinemaList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_CINEMALIST) == null) cinemaList = new HashMap<>();
        else cinemaList = (HashMap<Cineplex, ArrayList<Cinema>>) readSerializedObject(FILENAME_CINEMALIST);
    }

    /**
     * Reads booking history (a {@code ArrayList<BookingHistory>})
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readBookingHistory() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_BOOKINGHISTORY) == null) bookingHistory = new ArrayList<>();
        else bookingHistory = (ArrayList<BookingHistory>) readSerializedObject(FILENAME_BOOKINGHISTORY);
    }

    /**
     * Reads review list (a {@code HashMap<Movie, ArrayList<Review>>})
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readReviewList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_REVIEWLIST) == null) reviewList = new HashMap<>();
        else reviewList = (HashMap<Movie, ArrayList<Review>>) readSerializedObject(FILENAME_REVIEWLIST);
    }

    /**
     * Reads holiday list (a {@code HashMap<String, Holiday>})
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readHolidayList() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_HOLIDAY) == null) holidayList = new HashMap<>();
        else holidayList = (HashMap<String, Holiday>) readSerializedObject(FILENAME_HOLIDAY);
    }

    /**
     * Reads system (a {@code HashMap<String, Boolean>})
     * @throws IOException when the file is not found
     * @throws ClassNotFoundException when the class is not found
     */
    @SuppressWarnings("unchecked")
    private static void readSystem() throws IOException, ClassNotFoundException {
        if (readSerializedObject(FILENAME_SYSTEM) == null) system = new HashMap<>();
        else system = (HashMap<String, Boolean>) readSerializedObject(FILENAME_SYSTEM);
    }

    /**
     * Updates movie listing file.
     * @throws IOException when the file address is invalid
     */
    public static void updateMovieListing() throws IOException {
        writeSerializedObject(FILENAME_MOVIE, movieListing);
    }

    /**
     * Updates showtime file.
     * @throws IOException when the file address is invalid
     */
    public static void updateShowtime() throws IOException {
        writeSerializedObject(FILENAME_SHOWTIME, movieShowtime);
    }

    /**
     * Updates cinema list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateCinemaList() throws IOException {
        writeSerializedObject(FILENAME_CINEMALIST, cinemaList);
    }

    /**
     * Updates booking history file.
     * @throws IOException when the file address is invalid
     */
    public static void updateBookingHistory() throws IOException {
        writeSerializedObject(FILENAME_BOOKINGHISTORY, bookingHistory);
    }

    /**
     * Updates review list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateReviewList() throws IOException {
        writeSerializedObject(FILENAME_REVIEWLIST, reviewList);
    }

    /**
     * Updates holiday list file.
     * @throws IOException when the file address is invalid
     */
    public static void updateHolidayList() throws IOException {
        writeSerializedObject(FILENAME_HOLIDAY, holidayList);
    }

    /**
     * Updates system file.
     * @throws IOException when the file address is invalid
     */
    public static void updateSystem() throws IOException {
        writeSerializedObject(FILENAME_SYSTEM, system);
    }

    /**
     * Gets movie listing (an {@code ArrayList<Movie>}).
     * @return the movie listing (an {@code ArrayList<Movie>})
     */
    public static ArrayList<Movie> getMovieListing() {
        return movieListing;
    }

    /**
     * Gets the top 5 rankings (an {@code ArrayList<Movie>}).
     * @return top 5 ranking by overall rating when orderBy is true, top 5 ranking by ticket sales when orderBy is false
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Movie> getTop5MovieListing() {
        boolean orderBy = system.get("movieOrder");
        ArrayList<Movie> top5 = new ArrayList<>();
        for (Movie movie : movieListing) {
            if (!movie.getMovieStatus().equals(MovieStatus.END_OF_SHOWING)) top5.add(movie);
        }

        if (orderBy) {  // order by overall ratings
            Collections.sort(top5, (o1, o2) -> Double.compare(getMovieRating(o2), getMovieRating(o1)));
        }
        else {  // order by ticket sales
            Collections.sort(top5, (o1, o2) -> Integer.compare(o2.getSales(), o1.getSales()));
        }

        while (top5.size() > 5) {
            top5.remove(5);
        }

        return top5;
    }

    /**
     * Gets showtime by movie.
     * @param movie the movie
     * @return the showtime of the movie
     */
    public static ArrayList<Showtime> getMovieShowtime(Movie movie) {
        return movieShowtime.get(movie);
    }

    /**
     * Gets the cinema list (an {@code ArrayList<Cinema>}) by cineplex.
     * @param cineplex the cineplex
     * @return the cinema list
     */
    public static ArrayList<Cinema> getCinemaList(Cineplex cineplex) {
        return cinemaList.get(cineplex);
    }

    /**
     * Gets the booking history (an {@code ArrayList<BookingHistory>}).
     * @return the booking history
     */
    public static ArrayList<BookingHistory> getBookingHistory() {
        return bookingHistory;
    }

    /**
     * Gets the review list (an {@code ArrayList<Review>}) by movie.
     * @param movie the movie
     * @return the review list
     */
    public static ArrayList<Review> getReviewList(Movie movie) {
        return reviewList.get(movie);
    }

    /**
     * Gets the holiday list (an {@code HashMap<String, Holiday>}).
     * @return the holiday list
     */
    public static HashMap<String, Holiday> getHolidayList() {
        return holidayList;
    }

    /**
     * Gets the system setting (an {@code HashMap<String, Boolean>}).
     * @return the system setting
     */
    public static HashMap<String, Boolean> getSystem() { return system; }

    /**
     * Gets {@code Cinema} by cinema code.
     * @param code the cinema code
     * @return the cinema
     */
    public static Cinema getCinemaByCode(String code) {
        for (Cineplex cineplex : Cineplex.values()) {
            if (getCinemaList(cineplex) == null) continue;
            for (Cinema cinema : getCinemaList(cineplex)) {
                if (cinema.getCode().equals(code)) return cinema;
            }
        }
        return null;
    }

    /**
     * Gets the search result (an {@code ArrayList<Movie>}
     * @param title the title to be searched
     * @return the movie list
     */
    public static ArrayList<Movie> getMovieByTitle(String title) {
        ArrayList<Movie> searchResult = new ArrayList<>();
        for (Movie movie: movieListing) {
            if (movie.getTitle().toUpperCase().contains(title.toUpperCase())) searchResult.add(movie);
        }
        return searchResult;
    }

    /**
     * Gets the average rating ({@code double}) of a movie.
     * @param movie the movie to get average rating
     * @return the average rating of the movie
     */
    public static double getMovieRating(Movie movie) {
        ArrayList<Review> reviewList = getReviewList(movie);
        if (reviewList == null || reviewList.isEmpty()) return 0;
        else {
            double sum = 0;
            for (Review review : reviewList) sum += review.getRating();
            return round(sum / reviewList.size(), 1);
        }
    }

    /**
     * Gets the holiday by {@code Date}.
     * @param time the {@code Date} of the holiday
     * @return the holiday
     */
    public static Holiday getHoliday(Date time) {
        HashMap<String, Holiday> holidayList = getHolidayList();
        return holidayList.get(formatTimeMMdd(time));
    }

    /**
     * Adds new movie to movie listing
     * @param movie the movie to be added
     * @throws IOException when the file address is invalid
     */
    public static void addNewListing(Movie movie) throws IOException{
        movieListing.add(movie);
        updateMovieListing();
    }

    /**
     * Adds showtime to the showtime list of a movie and update local files.
     * @param showtime the showtime to be added
     * @throws IOException when the file address is invalid
     */
    public static void addShowtime(Showtime showtime) throws IOException {
        Movie movie = showtime.getMovie();
        if (movieShowtime.get(movie) == null) movieShowtime.put(movie, new ArrayList<>());
        movieShowtime.get(movie).add(showtime);
        updateShowtime();
    }

    /**
     * Updates booking history
     * @param record the new booking record
     * @throws IOException when the file address is invalid
     */
    public static void addBooking(BookingHistory record) throws IOException {
        bookingHistory.add(record);
        updateBookingHistory();
    }

    /**
     * Adds new review to a movie
     * @param movie the movie got reviewed
     * @param review the review
     * @throws IOException when the file address is invalid
     */
    public static void addNewReview(Movie movie, Review review) throws IOException {
        if(reviewList.get(movie) == null) reviewList.put(movie, new ArrayList<>());
        reviewList.get(movie).add(review);
        updateReviewList();
    }

    /**
     * Adds new cinema to the cinema list
     * @param cinema the cinema to be added
     * @throws IOException when the file address is invalid
     */
    public static void addCinema(Cinema cinema) throws IOException {
        if (cinemaList.get(cinema.getCineplex()) == null) cinemaList.put(cinema.getCineplex(), new ArrayList<>());
        cinemaList.get(cinema.getCineplex()).add(cinema);
    }

    /**
     * Adds holiday to the holiday list
     * @param date the date of the holiday
     * @param holiday the holiday
     * @throws IOException when the file address is invalid
     */
    public static void addHoliday(String date, Holiday holiday) throws IOException {
        holidayList.put(date, holiday);
        updateHolidayList();
    }

    /**
     * Removes movie from movie list
     * @param movie the movie to be removed
     * @throws IOException when the file address is invalid
     */
    public static void removeMovieListing(Movie movie) throws IOException {
        movie.setMovieStatus(MovieStatus.END_OF_SHOWING);
        updateMovieListing();
    }

    /**
     * Removes showtime from the showtime list
     * @param showtime the showtime to be removed
     * @throws IOException when the file address is invalid
     */
    public static void removeShowtime(Showtime showtime) throws IOException {
        movieShowtime.get(showtime.getMovie()).remove(showtime);
        updateShowtime();
    }

    /**
     * Removes all showtime of a movie
     * @param movie the movie to remove all its showtime
     * @throws IOException when the file address is invalid
     */
    public static void removeAllShowtime(Movie movie) throws IOException {
        movieShowtime.remove(movie);
        updateShowtime();
    }

    /**
     * Authenticate if username matches password.
     * @param username the username
     * @param password the password
     * @return true if the password matches username, else false
     */
    public static boolean authentication (String username, String password) {
        if (staffAccount.get(username) == null) return false;  // username does not exist
        else return staffAccount.get(username).equals(password);  // password does not match
    }
}
