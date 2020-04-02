package com.utwente.soa.voting.services;

import com.utwente.soa.voting.entity.MovieSuggestionEntity;


import java.util.List;

public interface VotingService {
   // void addMovie(MovieXmlRequest movie); //save
    List<MovieSuggestionEntity> showVotingList(); //get all
    MovieSuggestionEntity voteMovie(String idbmID); //update

}
