package com.utwente.soa.team8MovieProject.services.consumer;
import com.utwente.soa.team8MovieProject.integrations.request.MovieXmlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class MovieMessageConsumer {

    @Value("${list.of.movies}")
    private List <String> movies;

    //consumes the message from queue in destination
    @JmsListener(destination = "${queue.voting}")
    public void receiveMessage(MovieXmlRequest movie) {
        movies.add(movie.getMovieIdmbID());
        System.out.println(movies.toString());
        //do something

    }
    @JmsListener(destination = "${queue.dlq}")
    public void receiveMessage2() {

    }
}
