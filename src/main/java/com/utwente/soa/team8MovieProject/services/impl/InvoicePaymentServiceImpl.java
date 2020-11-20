package com.utwente.soa.team8MovieProject.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utwente.soa.team8MovieProject.services.InvoicePaymentService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class InvoicePaymentServiceImpl implements InvoicePaymentService {

    @Override
    public String pay(int movieId) {
        //make a sync request to 3rd party Postman mock service payments
        RestTemplate restTemplate = new RestTemplate();

     //sync request to payment service
        return restTemplate.getForObject("https://a3a3d2a0-13a6-4d97-b64b-a0fe91211707.mock.pstmn.io/process-payment", String.class);

    }
}
