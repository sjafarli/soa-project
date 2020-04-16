package com.utwente.soa.team8MovieProject.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.utwente.soa.team8MovieProject.dto.Movie;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@CrossOrigin
@RequestMapping("/cinema")
@Api
public class IndexController {

    @Autowired
    private SearchService searchService;

    @ApiOperation(value = "Retrieves all movies stored locally", response = List.class)
    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return searchService.getMovies();
    }

    @ApiOperation(value = "Searches movie locally with query word, if not found then suggests from imdb shown", response = String.class)
    @RequestMapping(path = "movies/search", method = RequestMethod.GET)
    public List<Movie> searchMovie(@RequestParam String query) {
        return searchService.search(query);
    }

    @ApiOperation(value = "Adds a movie to local storage, information retrieved from imdb", response = String.class)
    @RequestMapping(path = "/movies/add", method = RequestMethod.PUT)
    public Movie addMovie(@RequestParam String id) {
        return searchService.addMovie(id);
    }

    @ApiOperation(value = "Searches for a unique movie locally based on the imdb id", response = String.class)
    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable String id) throws NoSuchElementException {
        return searchService.getMovie(id);
    }

    @ApiOperation(value = "Removes a movie from local storage", response = String.class)
    @RequestMapping(path = "/movie/{id}/remove", method = RequestMethod.DELETE)
    public Movie removeMovie(@PathVariable String id) throws NoSuchElementException {
        return searchService.removeMovie(id);
    }

    //when user cannot find the movie and presses the add movie button, this endpoint is called, which simply adds msg to queue
    @ApiOperation(value = "Suggest a movie for further voting list", response = String.class)
    @RequestMapping(path = "voting/suggest", method = RequestMethod.POST)
    public ResponseEntity<String> addMovieToVotingList(@RequestParam String id) {
        return searchService.submitSuggestion(id);
    }
}