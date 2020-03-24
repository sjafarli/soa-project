package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import com.utwente.soa.team8MovieProject.services.producer.MovieMessageProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.utwente.soa.team8MovieProject.Movie;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    MovieMessageProducer votingQueueClient;

    // private VotingQueueClient votingQueueClient = new VotingQueueClient();

    @ApiOperation(value = "Accepts the payment for the specified movie", response = String.class)
    @RequestMapping(path = "/movie/{id}/payment", method = RequestMethod.GET)

    public String buyTicket ( @PathVariable int id){
        return invoicePaymentService.pay(id);
    }

    @ApiOperation(value = "Retrieves all movies stored locally", response = List.class)
   // @RequestMapping(path = "movies", method = RequestMethod.GET)
    public List<Movie> getMovies() {
        return searchService.getMovies();
    }

    @ApiOperation(value = "Searches for a unique movie locally based on the imdb id", response = String.class)
    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovie (@PathVariable String id) throws NoSuchElementException {
        return searchService.getMovie(id);
    }

    @ApiOperation(value = "Searches movie locally with query word, if not found then suggests from imdb shown", response = String.class)
    @RequestMapping(path = "/search",method = RequestMethod.GET)
    public List<Movie> searchMovie(@RequestParam String query) {
        return searchService.search(query);
    }

        @RequestMapping(path = "/movie/{id}/remove", method = RequestMethod.DELETE)
        public Movie removeMovie (@PathVariable String id) throws NoSuchElementException {
            return searchService.removeMovie(id);
        }

        @RequestMapping(path = "/movie/add", method = RequestMethod.PUT)
        public Movie addMovie (@RequestParam String id){
            return searchService.addMovie(id);
        }

        //when user cannot find the movie and fills the add movie button, this endpoint is called, which simply adds msg to queue
        @ApiOperation(value = "Suggest a movie for further voting list", response = String.class)
        @RequestMapping(path = "/{id}/suggest", method = RequestMethod.POST)
        public ResponseEntity<String> addMovieToVotingList (@RequestParam String movieID){
             return searchService.submitSuggestion(movieID);
        }

    }