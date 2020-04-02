package com.utwente.soa.voting.controller;


import com.utwente.soa.voting.entity.MovieSuggestionEntity;
import com.utwente.soa.voting.services.VotingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cinema")
@Api(value = "Best Cinema")
public class IndexController {

    @Autowired
    private VotingService votingService;

    @ApiOperation(value = "Vote for a specific movie", response = String.class)
    @RequestMapping(path = "voting/{id}", method = RequestMethod.PUT)
    public MovieSuggestionEntity voteMovie(@PathVariable String id) {
        return votingService.voteMovie(id);
    }

    @ApiOperation(value = "Retrieve voting list of movies", response = String.class)
    @RequestMapping(path = "/voting/list", method = RequestMethod.GET) //should have the same endpoint with movies?yy
    public List<MovieSuggestionEntity> getWishListedMovies(){
        return votingService.showVotingList();
    }
}