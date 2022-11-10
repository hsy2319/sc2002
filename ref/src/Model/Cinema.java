package Model;

import Model.Constant.*;

import java.io.Serializable;

/**
 * Contains information of a cinema: cineplex,
 * if it is platinum, if it is 3D, the cinema code and the
 * base price.
 *
 * @version 1.0
 */
public class Cinema implements Serializable {
    private Cineplex cineplex;
    private boolean isPlatinum;
    private boolean is3D;
    private String code;
    private double basePrice;

    public Cinema(Cineplex cineplex, boolean isPlatinum, boolean is3D, String code, double basePrice) {
        this.cineplex = cineplex;
        this.isPlatinum = isPlatinum;
        this.is3D = is3D;
        this.code = code;
        this.basePrice = basePrice;
    }

    /**
     * Checks if cinema is platinum.
     * @return true if the cinema is platinum, else false
     */
    public boolean isPlatinum() {
        return isPlatinum;
    }

    /**
     * Get the cineplex of the cinema.
     * @return the cineplex of the cinema
     */
    public Cineplex getCineplex() {
        return cineplex;
    }

    /**
     * Gets the cinema code.
     * @return the cinema code
     */
    public String getCode() {
        return code;
    }

    /**
     * Checks if cinema is 3D.
     * @return true if the cinema is 3d, else false
     */
    public boolean is3D() {
        return is3D;
    }

    /**
     * Sets the base price of the cinema
     * @param basePrice the base price to be assigned
     */
    public void setBasePrice(double basePrice ) { this.basePrice = basePrice; }

    /**
     * Gets the base price of the cinema
     * @return the base price of the cinema
     */
    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cinema cinema = (Cinema) o;

        return code.equals(cinema.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }
}