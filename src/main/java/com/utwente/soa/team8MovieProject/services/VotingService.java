package com.utwente.soa.team8MovieProject.services;

import com.utwente.soa.team8MovieProject.dto.MovieRequestDTO;
import com.utwente.soa.team8MovieProject.integrations.request.MovieXmlRequest;

import java.util.List;

public interface VotingService {
    void addMovie(MovieXmlRequest movie);
    List<MovieRequestDTO> showVotingList();
    MovieRequestDTO voteMovie(String idbmID);

}
