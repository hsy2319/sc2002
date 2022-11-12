/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import com.google.common.base.Joiner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Database {
        public Database() { }

    private static String getLocation(String str) {
        return "src/Data/" + str + ".csv";
    }
    
    public static ArrayList<Movie> loadMovies(String search) {
        BufferedReader reader = null;
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Movie")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[1].toLowerCase().contains(search.toLowerCase())) {
                    List<String> cast = Arrays.asList(tokens[5].split("\\."));
                    ArrayList<String> reviews=new ArrayList<String>(Arrays.asList(tokens[8].split("\\.")));
                    if(reviews.get(0).equals(""))
                        reviews=new ArrayList<>();
                    List<String> temp= Arrays.asList(tokens[3].split("\\."));

                    ArrayList<Integer> ratings=new ArrayList<>();
                    if(!temp.get(0).equals(""))
                        for (String s : temp) ratings.add(Integer.valueOf(s));

                    Movie movie = new Movie(Integer.parseInt(tokens[0]), tokens[1], tokens[2],ratings, tokens[4], cast, tokens[6], tokens[7],reviews,tokens[9]);
                    movieArrayList.add(movie);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

        return movieArrayList;
    }

    public static Boolean manageMovie(Movie movie){

        File inputFile = new File(getLocation("Movie"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append("Movie ID");
            writer.append(",");
            writer.append("Movie Name");
            writer.append(",");
            writer.append("Language");
            writer.append(",");
            writer.append("Rating");
            writer.append(",");
            writer.append("RunTime");
            writer.append(",");
            writer.append("Cast");
            writer.append(",");
            writer.append("Director");
            writer.append(",");
            writer.append("Synopsis");
            writer.append(",");
            writer.append("Reviews");
            writer.append(",");
            writer.append("Status");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if (Integer.parseInt(tokens[0])==movie.getId()) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append(",");
                    writer.append(tokens[7]);
                    writer.append(",");
                    writer.append(tokens[8]);
                    writer.append(",");
                    writer.append(tokens[9]);
                    writer.append("\n");
                }

                if (Found) {
                    writer.append(String.valueOf(movie.getId()));
                    writer.append(",");
                    writer.append(movie.getName());
                    writer.append(",");
                    writer.append(movie.getLang());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(movie.getRating()));
                    writer.append(",");
                    writer.append(movie.getRunTime());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(movie.getCast()));
                    writer.append(",");
                    writer.append(movie.getDirector());
                    writer.append(",");
                    writer.append(movie.getSynopsis());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(movie.getReviews()));
                    writer.append(",");
                    writer.append(movie.getStatus());
                    writer.append("\n");
                    Found = false;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Movie")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Movie")));
        return success;

    }

    public static Boolean saveMovies(Movie movie) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("Movie"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(movie.getId()));
            writer.append(",");
            writer.append(movie.getName());
            writer.append(",");
            writer.append(movie.getLang());
            writer.append(",");
            writer.append(Joiner.on('.').join(movie.getRating()));
            writer.append(",");
            writer.append(movie.getRunTime());
            writer.append(",");
            writer.append(Joiner.on('.').join(movie.getCast()));
            writer.append(",");
            writer.append(movie.getSynopsis());
            writer.append(",");
            writer.append(movie.getDirector());
            writer.append(",");
            writer.append(Joiner.on('.').join(movie.getReviews()));
            writer.append(",");
            writer.append(movie.getStatus());
            writer.append("\n");
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;


    }
    
//NEW    
    public static ArrayList<Cineplex> loadCineplex() {
        BufferedReader reader = null;
        ArrayList<Cineplex> cineplexArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Cineplex")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                Cineplex cineplex = new Cineplex(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4]);
                cineplexArrayList.add(cineplex);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return cineplexArrayList;
    }
    public static List<Cinema> loadShowTimes(int movieID) {

        String ID=String.valueOf(movieID);

        if(movieID==-1)
            ID="";

        BufferedReader reader = null;
        ArrayList<Cinema> cinemaArrayList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(getLocation("Cinema")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[1].contains(ID)) {
                    List<Integer> seats = new ArrayList<>();
                    if(!tokens[4].equals("")){
                    List<String> str = Arrays.asList(tokens[4].split("\\."));
                    for (String s : str) seats.add(Integer.valueOf(s));}
                    Cinema cinema = new Cinema(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2], tokens[3],seats,tokens[5]);
                    cinemaArrayList.add(cinema);
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return cinemaArrayList;
    }    
    
    public static Boolean addShowtimes(Cinema cinema) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(getLocation("Cinema"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.append(String.valueOf(cinema.getCinplexID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getCinemaID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getMovieID()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getTime()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getCinemaClass()));
            writer.append(",");
            writer.append(Joiner.on('.').join(cinema.getSeats()));
            writer.append(",");
            writer.append(String.valueOf(cinema.getMovieType()));
            writer.append("\n");
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param cinema
     * @return Ture if Showtime removed successfully
     */
    public static Boolean removeShowtime(Cinema cinema) {


        File inputFile = new File(getLocation("Cinema"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Cineplex_ID");
            writer.append(",");
            writer.append("Cinema_ID");
            writer.append(",");
            writer.append("Movie ID");
            writer.append(",");
            writer.append("ShowTime");
            writer.append(",");
            writer.append("Class");
            writer.append(",");
            writer.append("Seats");
            writer.append(",");
            writer.append("MovieType");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if ((tokens[0].equals(String.valueOf(cinema.getCinplexID()))) && (tokens[1].equals(String.valueOf(cinema.getCinemaID()))) && (tokens[2].equals(String.valueOf(cinema.getMovieID()))) && (tokens[3].contains(String.valueOf(cinema.getTime())))) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append("\n");
                }

                if (Found)
                    Found = false;



            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Cinema")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Cinema")));
        return success;


    }

    /**
     *Updates the cinema details in the database, accepts two cinema objects, one as a key and another for details to be updated
     * @param cinema1 Cinema object to be used as a key to determine which entry line in database to be updated
     * @param cinema2 Cinema object to be updated in the database
     * @return True if showTime updated successfully updated, False otherwise
     */
    public static Boolean updateShowtime(Cinema cinema1, Cinema cinema2) {

        File inputFile = new File(getLocation("Cinema"));
        File tempFile = new File(getLocation("Temp"));

        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            writer.append("Cineplex_ID");
            writer.append(",");
            writer.append("Cinema_ID");
            writer.append(",");
            writer.append("Movie ID");
            writer.append(",");
            writer.append("ShowTime");
            writer.append(",");
            writer.append("Class");
            writer.append(",");
            writer.append("Seats");
            writer.append(",");
            writer.append("MovieType");
            writer.append("\n");


        } catch (IOException e) {
            e.printStackTrace();
        }
        Boolean Found = false;
        String line;
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if ((tokens[0].equals(String.valueOf(cinema1.getCinplexID()))) && (tokens[1].equals(String.valueOf(cinema1.getCinemaID()))) && (tokens[2].equals(String.valueOf(cinema1.getMovieID()))) && (tokens[3].contains(String.valueOf(cinema1.getTime())))) {
                    Found = true;
                } else {
                    writer.append(tokens[0]);
                    writer.append(",");
                    writer.append(tokens[1]);
                    writer.append(",");
                    writer.append(tokens[2]);
                    writer.append(",");
                    writer.append(tokens[3]);
                    writer.append(",");
                    writer.append(tokens[4]);
                    writer.append(",");
                    writer.append(tokens[5]);
                    writer.append(",");
                    writer.append(tokens[6]);
                    writer.append("\n");
                }

                if (Found) {
                    writer.append(String.valueOf(cinema2.getCinplexID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getCinemaID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getMovieID()));
                    writer.append(",");
                    writer.append(String.valueOf(cinema2.getTime()));
                    writer.append(",");
                    writer.append(cinema2.getCinemaClass());
                    writer.append(",");
                    writer.append(Joiner.on('.').join(cinema2.getSeats()));
                    writer.append(",");
                    writer.append(cinema2.getMovieType());
                    writer.append("\n");
                    Found = false;

                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //
            writer.close();
            reader.close();


            Files.delete(Paths.get(getLocation("Cinema")));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Boolean success = tempFile.renameTo(new File(getLocation("Cinema")));
        return success;


    }    
    
}
