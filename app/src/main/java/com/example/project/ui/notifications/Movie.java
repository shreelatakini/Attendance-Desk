package com.example.project.ui.notifications;


public class Movie {

    // Store the id of the  movie poster
    private int nIcon;
    // Store the name of the movie
    private String nName;
    // Store the release date of the movie
    private String nDetails ;

    // Constructor that is used to create an instance of the Movie object
    public Movie(int mIcon, String mName, String mDetails) {
        this.nIcon = mIcon;
        this.nName = mName;
        this.nDetails = mDetails;
    }

    public int getnIcon() {
        return nIcon;
    }

    public void setnIcon(int mIcon) {
        this.nIcon = mIcon;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String mName) {
        this.nName = mName;
    }

    public String getnDetails() {
        return nDetails;
    }

    public void setnDetails(String mDetails) {
        this.nDetails = mDetails;
    }
}
