package com.utwente.soa.voting.services.consumer;

import com.utwente.soa.voting.entity.MovieSuggestionEntity;
import com.utwente.soa.voting.exceptions.MovieAlreadyExistsException;
import com.utwente.soa.voting.integrations.MovieXmlRequest;
import com.utwente.soa.voting.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieMessageConsumer {
    //
    @Autowired
    VotingRepository votingRepository;

    @Value("${list.of.movies}")
    private List<String> movies;

    //consumes the message from queue in destination
    @JmsListener(destination = "${queue.voting}")
    public void receiveMessage(MovieXmlRequest movie) {

        MovieSuggestionEntity movieSuggestionEntity = votingRepository.findByimdbId(movie.getMovieIdmbID());
        if (movieSuggestionEntity != null) {
            throw new MovieAlreadyExistsException(movie.getMovieIdmbID());

        } else {
            MovieSuggestionEntity movieSuggestionEntity1 = new MovieSuggestionEntity();
            movieSuggestionEntity1.setImdbId(movie.getMovieIdmbID());
            movieSuggestionEntity1.setName(movie.getName());

            //add to voting database if there is a msg in queue
            //unique will be the field
            votingRepository.save(movieSuggestionEntity1);
        }

    }

    @JmsListener(destination = "${queue.dlq}")
    public void receiveMessage2() {

    }
}
