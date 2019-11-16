package com.example.webfluxdemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.webfluxdemo.model.Tweet;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
public class ConsumerTweetController {

	WebClient webClient = WebClient.create("http://localhost:7070");

	//Get all Tweets
    @GetMapping("/tweets")
    public Flux<Tweet> getAllTweets() {
    	
    	return webClient
    			.get()
    			.uri("/tweets")
    	        .retrieve()
                .bodyToFlux(Tweet.class);
    }
    
    
  //Save all Tweet by 3 ways of body

    @PostMapping("/tweets/BodyInserters")
    public Mono<Tweet> createTweetsBodyInserters(@Valid @RequestBody Tweet tweet) {
        return webClient
                .post()
                .uri("/tweets")
                .body(BodyInserters.fromObject(tweet))
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    @PostMapping("/tweets/syncBody")
    public Mono<Tweet> createTweetsSyncBody(@Valid @RequestBody Tweet tweet) {
        return webClient
                .post()
                .uri("/tweets")
                .syncBody(tweet)
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    
    @PostMapping("/tweets/justBody")
    public Mono<Tweet> createTweetsBody(@Valid @RequestBody Tweet tweet) {
        return webClient
                .post()
                .uri("/tweets")
                .body(Mono.just(tweet), Tweet.class)
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    

    @GetMapping("/tweets/pathParam/{id}")
    public Mono<Tweet> getTweetById(@PathVariable(value = "id") String tweetId) {
        return webClient
                .get()
                .uri("/tweets/pathParam/{id}", tweetId)
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }
    
    @GetMapping("/tweets/queryParam")
    public Mono<Tweet> getTweetByIdQueryParam(@RequestParam(value = "id") String tweetId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/tweets/byQueryParam")
                        .queryParam("id", tweetId)
                        .build())
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }
    
    
    @PutMapping("/tweets/update/{id}")
    public Mono<Tweet> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Tweet tweet) {
    	return webClient
                .post()
                .uri("/tweets/update/{id}", tweetId)
                .body(Mono.just(tweet), Tweet.class)
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }

    @DeleteMapping("/tweets/delete/{id}")
    public Mono<Void> deleteTweet(@PathVariable(value = "id") String tweetId) {

    	return webClient
                .delete()
                .uri("/tweets/delete/{id}", tweetId)
        		.retrieve()
        		.bodyToMono(Void.class);
    }




}
