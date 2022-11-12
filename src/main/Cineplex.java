package main;

//Cineplex is like shaw - JE, shaw - CCK
public enum Cineplex {
    JEM("JEM"),
    TheCathay("The Cathay"),
    WestMall("West Mall"),
    AMK_Hub("AMK Hub");

    private String cineplex;

    Cineplex(String cineplex) {
        this.cineplex = cineplex;
    }
}