package com.utwente.soa.voting.repository;

import com.utwente.soa.voting.entity.MovieSuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends JpaRepository<MovieSuggestionEntity, Long> {
    MovieSuggestionEntity findByimdbId(String imdb_id);
}
