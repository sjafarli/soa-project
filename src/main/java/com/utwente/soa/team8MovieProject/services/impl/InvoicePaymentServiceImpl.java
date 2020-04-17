package com.utwente.soa.team8MovieProject.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utwente.soa.team8MovieProject.exceptions.MovieNotFoundException;
import com.utwente.soa.team8MovieProject.exceptions.PaymentUnsuccessfulException;
import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;


@Service
public class InvoicePaymentServiceImpl implements InvoicePaymentService {

    @Override
    public String pay(String movieId) {
        RestTemplate restTemplate = new RestTemplate();
        //check if the movieId exists in the search
        try{
            Object movie = restTemplate.getForObject("http://searchservice:8084/cinema/movie/{id}", String.class,movieId);
            
        }catch (HttpServerErrorException.InternalServerError e){
            throw new MovieNotFoundException();
        }

        //make a sync request to 3rd party Postman mock service payments



     //sync request to payment service
        try {
            return restTemplate.getForObject("https://1bb6facd-608a-42a0-bc58-171bd0386383.mock.pstmn.io/payment", String.class);
        }
        catch (Exception e){
            throw new PaymentUnsuccessfulException();
        }

    }
}
