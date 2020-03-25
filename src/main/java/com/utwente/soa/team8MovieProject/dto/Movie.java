package com.utwente.soa.team8MovieProject.dto;

//object for local movie database
public class Movie {
    private String imdb_id;
    private String name;
    private String year;
    private String description;
    private boolean available;

    public Movie(String imdb_id, String name, String year, String description, boolean available) {
        this.imdb_id = imdb_id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.available = available;
    }

    public String getImdb_id() { return imdb_id; }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }
}
