package com.ilan.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TweetNotFoundException extends ResponseStatusException {

    public TweetNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }

    public TweetNotFoundException(HttpStatus status, String message, Throwable e) {
        super(status, message, e);
    }


}
