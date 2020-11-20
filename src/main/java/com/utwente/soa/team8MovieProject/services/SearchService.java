package com.utwente.soa.team8MovieProject.services;

import com.utwente.soa.team8MovieProject.dto.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SearchService {

    //search movie locally based on the query, if not found, search it on imdb and show suggestions
    List<Movie> search(String query);

    //get movie based on the imdb id locally
    Movie getMovie(String imdb_id);

    //retrieve all movies locally
    List<Movie> getMovies();

    Movie addMovie(String imdb_id);

    Movie removeMovie(String imdb_id);

    ResponseEntity<String> submitSuggestion(String imdb_id);
}
