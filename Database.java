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

    
}
