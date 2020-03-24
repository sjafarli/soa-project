package com.utwente.soa.team8MovieProject.services.producer;

import com.utwente.soa.team8MovieProject.integrations.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("voting") private String votingQueue;

    private MovieRequest movieRequest = new MovieRequest();

    public String addMovieToQueue(String id) {

       movieRequest.setMovieIdmbID(id);
       System.out.println(votingQueue);
        jmsTemplate.convertAndSend(votingQueue, movieRequest);
        return "added to queue";
    }
}
