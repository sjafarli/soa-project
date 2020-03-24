package com.utwente.soa.team8MovieProject.services;

import com.utwente.soa.team8MovieProject.Movie;

import java.util.List;

public interface SearchService {
    List<Movie> search(String query);

    Movie getMovie(String imdb_id);

    List<Movie> getMovies();

    Movie addMovie(String imdb_id);

    Movie removeMovie(String imdb_id);

    void submitSuggestion(String imdb_id);
}
