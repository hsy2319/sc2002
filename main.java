/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.Scanner;
public class main {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Admin or MovieGoer?(1 for Admin and 2 for MovieGoer):");
        int user = sc.nextInt();
        if(user==1)
        {
          AdminData admin = new AdminData();
          login l = new login(admin.getLoginData());
        }
        else if(user==2)
        {
          System.out.println("List movie;movie details;check seat;purchase tix;view history;ratings?");
        }
        else
        {
          System.out.println("Please enter either 1 or 2!");
        }
    }    
}
