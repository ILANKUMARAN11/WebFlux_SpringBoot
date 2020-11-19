package com.ilan.exception.handler;

import com.ilan.entity.Tweet;
import com.ilan.exception.model.TweetNotFoundException;
import com.ilan.exception.payload.ErrorResponse;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public Mono<ResponseEntity<Tweet>> handleTweetNotFoundException(TweetNotFoundException ex) {
        Tweet t=new Tweet();
        t.setText("IIII");
        t.setId("AA");
        return Mono.just(ResponseEntity.ok(t));
    }

}
