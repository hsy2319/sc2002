package Model;

import java.io.Serializable;

/**
 * Contains information of a moviegoer: name,
 * mobile number, email address and if they are senior citizen.
 *
 * @version 1.0
 */
public class Moviegoer implements Serializable {
    private final String name;
    private final String mobile;
    private final String email;
    private final boolean isSeniorCitizen;

    public Moviegoer(String name, String mobile, String email, boolean isSeniorCitizen) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.isSeniorCitizen = isSeniorCitizen;
    }

    /**
     * Checks if moviegoer is a senior citizen.
     * @return true if senior citizen, else false
     */
    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    /**
     * Gets the name of the moviegoer
     * @return the name of the moviegoer
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the mobile number of the moviegoer.
     * @return the mobile number of the moviegoer
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Gets the email of the moviegoer.
     * @return the email of the moviegoer.
     */
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moviegoer customer = (Moviegoer) o;

        if (!name.equals(customer.name)) return false;
        if (!mobile.equals(customer.mobile)) return false;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}