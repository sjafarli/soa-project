package com.utwente.soa.team8MovieProject.dto;

//object for voting database
public class MovieRequestDTO {

    private String name;
    private int votes;
    private String imdb_id;

    public void setName(String name) {
        this.name = name;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return this.votes;
    }

    @Override
    public String toString() {
        return "MovieRequestDTO{" +
                "imdb_id='" + imdb_id + '\'' +
                ", name='" + name + '\'' +
                ", votes=" + votes +
                '}';
    }


}
