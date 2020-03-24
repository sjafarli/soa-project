package com.utwente.soa.team8MovieProject.services;

import java.util.List;

public interface VotingService {
    String addMovie(String idmbID);
    List<String> showVotingList();
    void voteForMovie(String idbmID);

}
