package main;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.Scanner;
import View.*;
public class main {
    public static void main(String[] args)
    {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("1.admin");
    	System.out.println("2.viewer");
    	int input = scan.nextInt();
    	//start admin login
    	if(input == 1) {
    		AdminData admin = new AdminData();
		    login l = new login(admin.getLoginData());	
    	}
    	//display menu for viewer
    	else if (input == 2){
    		MoviegoerView moviegoerView = new MoviegoerView();
    		moviegoerView.start(scan);
    	}

    }
    
}
