package com.ilan.controller;

import com.ilan.entity.Tweet;
import com.ilan.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import javax.validation.Valid;


@RestController
@RequestMapping("/TweetProducer")
public class ProducerTweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping
    public Flux<Tweet> getAllTweets() {
        return tweetRepository.findAll().log();
    }

    @PostMapping
    public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
        return tweetRepository.save(tweet).log();
    }

    @GetMapping("/pathParam/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId) {
        return tweetRepository.findById(tweetId).log()
                .map(savedTweet -> ResponseEntity.ok(savedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/byQueryParam")
    public Mono<ResponseEntity<Tweet>> getTweetByIdRequestParam(@RequestParam(value = "id") String tweetId) {
        return tweetRepository.findById(tweetId).log()
                .map(savedTweet -> {
                	Tweet tt=savedTweet;
                	return ResponseEntity.ok(savedTweet);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Tweet tweet) {
        return tweetRepository.findById(tweetId).log()
                .flatMap(existingTweet -> {
                	Tweet tt=existingTweet;
                    existingTweet.setText(tweet.getText());
                    return tweetRepository.save(existingTweet);
                })
                .map(updateTweet -> {
                	Tweet tt=updateTweet;
                	ResponseEntity<Tweet> rs=new ResponseEntity<Tweet>(tt, HttpStatus.OK);
                	return rs;
                })
                .onErrorReturn(ResponseEntity.ok(tweet))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        
        
//         ResponseEntity<Tweet> rs=new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
//         return Mono.just(rs);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {

        return tweetRepository.findById(tweetId).log()
                .flatMap(existingTweet ->
                        tweetRepository.delete(existingTweet)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tweets are Sent to the client as Server Sent Events
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@GetMapping(value = "/stream/tweets", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Tweet> streamAllTweets() {
    	//return tweetRepository.findAll().log();
        return tweetRepository.findAll().log().delayElements(Duration.ofSeconds(5));
    }




}
