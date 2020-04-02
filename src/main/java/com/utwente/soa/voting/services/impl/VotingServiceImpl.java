package com.utwente.soa.voting.services.impl;

import com.utwente.soa.voting.entity.MovieSuggestionEntity;
import com.utwente.soa.voting.repository.VotingRepository;
import com.utwente.soa.voting.services.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VotingServiceImpl implements VotingService {

    @Autowired
    private VotingRepository votingRepository;


    //will change it to a Repository call
    @Override
    public List<MovieSuggestionEntity> showVotingList() {
        return votingRepository.findAll();
    }

    @Override
    public MovieSuggestionEntity voteMovie(String imdbID) {
        //TODO still throws errors, e.g if movie not in voting list or somehow twice?

       // MovieSuggestionDTO req = votingRepository.fin

        MovieSuggestionEntity request = votingRepository.findByimdbId(imdbID);

        int current_votes =request.getVotes();
        int newVotes =current_votes+1;
        System.out.println(newVotes);
        request.setVotes(newVotes);

        return votingRepository.save(request);
    }

}

