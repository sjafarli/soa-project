package com.utwente.soa.team8MovieProject.controller;


import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import com.utwente.soa.team8MovieProject.services.producer.MovieMessageProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.utwente.soa.team8MovieProject.services.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/movie")
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
    @RequestMapping(path = "/{id}/payment", method = RequestMethod.GET)

    public String buyTicket(@PathVariable int id) {
        return invoicePaymentService.pay(id);
    }

    @ApiOperation(value = "Search through movie db", response = ArrayList.class)
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ArrayList<String> searchMovie(@RequestParam String query) {
        return searchService.search(query);
    }

    //when user cannot find the movie and fills the add movie button, this endpoint is called, which simply adds msg to queue
    @ApiOperation(value = "Adds movie to voting list", response = String.class)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addMovieToVotingList(@RequestParam String movieID) {
        return votingQueueClient.addMovieToQueue(movieID);
    }

}