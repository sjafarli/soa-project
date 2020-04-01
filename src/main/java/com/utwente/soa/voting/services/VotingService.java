package com.utwente.soa.voting.services;

import com.utwente.soa.voting.dto.MovieRequestDTO;
import com.utwente.soa.voting.integrations.request.MovieXmlRequest;

import java.util.List;

public interface VotingService {
    void addMovie(MovieXmlRequest movie);
    List<MovieRequestDTO> showVotingList();
    MovieRequestDTO voteMovie(String idbmID);

}
