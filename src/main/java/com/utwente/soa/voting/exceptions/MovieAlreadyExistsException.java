package com.utwente.soa.voting.exceptions;

import javax.management.openmbean.KeyAlreadyExistsException;

public class MovieAlreadyExistsException extends KeyAlreadyExistsException {
    public MovieAlreadyExistsException(String id) {
        super("Movie with id "+ id+ " already exists in voting list");
    }
}
