package main;
import View.*;
public class Review {
	private Movie movie;
	private int rating;
	private String para;
	private String name;
	
	public Review(Movie movie,int rating,String para,String name) {
		this.movie = movie;
		this.rating = rating;
		this.para = para;
		this.name = name;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public int getRating() {
		return rating;
	}
	
	public String getPara() {
		return para;
	}
	
	public String getName() {
		return name;
	}

}
