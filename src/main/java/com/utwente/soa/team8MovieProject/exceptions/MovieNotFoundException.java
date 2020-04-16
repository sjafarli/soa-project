package com.utwente.soa.team8MovieProject.exceptions;

import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

public class MovieNotFoundException extends NoSuchElementException {

    public MovieNotFoundException() {
        super("Movie not found. You cannot pay for the movie that is not screening at the theatre");
    }

}
