/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.List;
import java.util.ArrayList;
public class Movie {
    int id;
    String name,lang,runTime,Synopsis,director,status;
    List<Integer> ratings;
    ArrayList<String> reviews;
    List<String> cast;
    
    public Movie(int id,String name,String lang,ArrayList<Integer> ratings,String runTime,List<String> cast,String director,String Synopsis,ArrayList<String> reviews,String status)
    {
        this.id = id;
        this.ratings = ratings;
        this.name = name;
        this.lang= lang;
        this.runTime = runTime;
        this.Synopsis = Synopsis;
        this.director = director;
        this.cast = cast;
        this.reviews = reviews;
        this.status = status;
    }

    public int getId() { 
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public List<Integer> getRating() {
        return ratings;
    }

    public void setRating(List<Integer> ratings) {
        this.ratings = ratings;
    }

    public void addRating(Integer rating){
        this.ratings.add(rating);
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getSynopsis() {
        return Synopsis;
    }

    public void setSynopsis(String Synopsis) {this.Synopsis = Synopsis; }

    public String getDirector() {
        return director;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String Lang) {
        lang = Lang;
    }

    public void setDirector(String Director) {
        director = Director;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getReviews(){
        if(reviews != null){
            return reviews;
        }else{
            return new ArrayList<String>();
        }
    }

    public void addReview(String review){
        this.reviews.add(review);
    }

    public void setReviews(ArrayList<String> reviews){
        this.reviews = reviews;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}
