package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.Movie;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private SearchService searchService;

    @RequestMapping (
            path = "movies",
            method = RequestMethod.GET
    )
    public List<Movie> getMovies() { return searchService.getMovies(); }

    @RequestMapping (
            path = "movies/search",
            method = RequestMethod.GET
    )
    public List<Movie> searchMovie(@RequestParam String query){
        return searchService.search(query);
    }

    @RequestMapping (
            path = "movie/{id}",
            method = RequestMethod.GET
    )
    public Movie getMovie(@PathVariable String id) throws NoSuchElementException { return searchService.getMovie(id); }

    @RequestMapping (
            path = "movie/{id}/remove",
            method = RequestMethod.DELETE
    )
    public Movie removeMovie(@PathVariable String id) throws NoSuchElementException { return searchService.removeMovie(id); }

    @RequestMapping (
            path = "movie/add",
            method = RequestMethod.PUT
    )
    public Movie addMovie(@RequestParam String id) { return searchService.addMovie(id); }

}