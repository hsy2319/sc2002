/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Essh
 */

public class Cineplex {
    /**
     * ID of the cineplex
     */
    private int id;

    /**
     * Name of cineplex and its 3 locations
     */
    private String name,loc1,loc2,loc3;


    /**
     * Creates the cineplex with the following parameters
**/
    public Cineplex(int id, String name, String loc1,String loc2,String loc3) {
        this.id = id;
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.loc3 = loc3;
        this.name = name;
    }

    /**
     * Get the ID of cineplex
     * @return ID of cineplex
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }
    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }
    public String getLoc3() {
        return loc3;
    }

    public void setLoc3(String loc3) {
        this.loc3 = loc3;
    }    

    /**
     * Get the name of cineplex
     * @return name of cineplex
     */
    public String getName() {
        return name;
    }

    /**
     * Change the name of cineplex
     * @param name name of cineplex
     */
    public void setName(String name) {
        this.name = name;
    }


}