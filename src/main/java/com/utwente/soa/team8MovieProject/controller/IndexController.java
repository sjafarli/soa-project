package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.dto.MovieRequestDTO;
import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import com.utwente.soa.team8MovieProject.services.VotingService;
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
@RequestMapping("/movies")
@Api
public class IndexController {

    @Autowired
    private InvoicePaymentService invoicePaymentService;
    @Autowired
    private SearchService searchService;
    @Autowired
    private VotingService votingService;



    @ApiOperation(value = "Accepts the payment for the specified movie", response = String.class)
    @RequestMapping(path = "/movie/{id}/payment", method = RequestMethod.GET)

    public String buyTicket(@PathVariable int id) {
        return invoicePaymentService.pay(id);
    }

    @ApiOperation(value = "Retrieves all movies stored locally", response = List.class)
    public List<Movie> getMovies() {
        return searchService.getMovies();
    }

    @ApiOperation(value = "Searches for a unique movie locally based on the imdb id", response = String.class)
    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable String id) throws NoSuchElementException {
        return searchService.getMovie(id);
    }

    @ApiOperation(value = "Searches movie locally with query word, if not found then suggests from imdb shown", response = String.class)
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public List<Movie> searchMovie(@RequestParam String query) {
        return searchService.search(query);
    }

    @RequestMapping(path = "/movie/{id}/removal", method = RequestMethod.DELETE)
    public Movie removeMovie(@PathVariable String id) throws NoSuchElementException {
        return searchService.removeMovie(id);
    }

    @RequestMapping(path = "/movie/adding", method = RequestMethod.PUT)
    public Movie addMovie(@RequestParam String id) {
        return searchService.addMovie(id);
    }

    //when user cannot find the movie and fills the add movie button, this endpoint is called, which simply adds msg to queue
    @ApiOperation(value = "Suggest a movie for further voting list", response = String.class)
    @RequestMapping(path = "/suggestion", method = RequestMethod.POST)
    public ResponseEntity<String> addMovieToVotingList(@RequestParam String movieID) {
        return searchService.submitSuggestion(movieID);
    }

    @ApiOperation(value = "Vote for a specific movie", response = String.class)
    @RequestMapping(path = "/{id}/voting", method = RequestMethod.PUT)
    public MovieRequestDTO voteMovie(@PathVariable String id) {
        return votingService.voteMovie(id);
    }

    @ApiOperation(value = "Retrieve voting list of movies", response = String.class)
    @RequestMapping(path = "/vlist", method = RequestMethod.GET) //should have the same endpoint with movies?yy
    public List<MovieRequestDTO> getWishListedMovies(){
        return votingService.showVotingList();
    }
}