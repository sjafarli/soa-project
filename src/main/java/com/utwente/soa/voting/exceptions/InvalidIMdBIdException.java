package com.utwente.soa.voting.exceptions;

import java.util.NoSuchElementException;

public class InvalidIMdBIdException extends NoSuchElementException {
    public InvalidIMdBIdException(String imdb_id) {
        super("No movie found with IMdB id: " + imdb_id);
    }
}
