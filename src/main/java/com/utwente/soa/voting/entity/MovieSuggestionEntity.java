package com.utwente.soa.voting.entity;

import javax.persistence.*;
import java.io.Serializable;

//object for voting database
@Entity
@Table(name="voting")
public class MovieSuggestionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "votes")
    private int votes;
    @Column(name = "imdb_id")
    private String imdbId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
}
