package main;

import java.util.Date;

public class Holiday {
	private String name;
    private Date date;
    private double price;
	    
    public Holiday(String name, Date date, double price) {
        this.name = name;
        this.date = date;
        this.price = price;
    }
    
    public String getName() {
    	return name;
    }
    
    public Date getDate() {
    	return date;
    }
    
    public double getPrice() { 
    	return price;
    }

}
