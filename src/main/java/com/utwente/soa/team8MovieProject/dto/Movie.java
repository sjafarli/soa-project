package com.utwente.soa.team8MovieProject.dto;

import lombok.Data;

//object for local movie database
@Data
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
    public Movie(){

    }

}
