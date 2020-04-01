package com.utwente.soa.voting.services.producer;

import com.utwente.soa.voting.integrations.request.MovieXmlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieMessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("voting") private String votingQueue;

    private MovieXmlRequest movieXmlRequest = new MovieXmlRequest();

    public String addMovieToQueue(String id) {

       movieXmlRequest.setMovieIdmbID(id);
       System.out.println(votingQueue);
        jmsTemplate.convertAndSend(votingQueue, movieXmlRequest);
        return "added to queue";
    }
}
