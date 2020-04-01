package com.utwente.soa.voting.controller;


import com.utwente.soa.voting.dto.MovieRequestDTO;
import com.utwente.soa.voting.services.VotingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/cinema")
@Api(value = "Best Cinema")
public class IndexController {

    @Autowired
    private VotingService votingService;

//
//    @ApiOperation(value = "Suggest a movie for further voting list", response = String.class)
//    @RequestMapping(path = "voting/suggest", method = RequestMethod.POST)
//    public ResponseEntity<String> addMovieToVotingList(@RequestParam String id) {
//        return searchService.submitSuggestion(id);
//    }

    @ApiOperation(value = "Vote for a specific movie", response = String.class)
    @RequestMapping(path = "voting/{id}", method = RequestMethod.PUT)
    public MovieRequestDTO voteMovie(@PathVariable String id) {
        return votingService.voteMovie(id);
    }

    @ApiOperation(value = "Retrieve voting list of movies", response = String.class)
    @RequestMapping(path = "/voting/list", method = RequestMethod.GET) //should have the same endpoint with movies?yy
    public List<MovieRequestDTO> getWishListedMovies(){
        return votingService.showVotingList();
    }


}