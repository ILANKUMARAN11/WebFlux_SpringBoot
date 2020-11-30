package com.ilan.controller;

import javax.validation.Valid;

import com.ilan.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/TweetConsumer")
public class ConsumerTweetController {



    @Autowired
    private WebClient.Builder webClientBuilder;


	//Get all Tweets
    @GetMapping
    public Flux<Tweet> getAllTweets() {



        WebClient webClients = WebClient.create();

        Tweet tw=webClients.get()
                .uri("http://WebFlux-Producer"+"/TweetProducer")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Tweet.class)
                .blockLast();


        return webClients.get()
    		     .uri("http://WebFlux-Producer"+"/TweetProducer")
    		     .accept(MediaType.APPLICATION_STREAM_JSON)
    		     .exchange()
    		     .flatMapMany(response -> {
    		    	 ClientResponse clientResponse=response;
    		    	 System.out.println(clientResponse.statusCode());
    		    	 return clientResponse.bodyToFlux(Tweet.class).log();
    		    	 }
    		   );



    	
//    	return webClients
//		.get()
//		.uri("http://WebFlux-Producer"+"/TweetProducer")
//        .retrieve()
//        .bodyToFlux(Tweet.class).log();
    	
    	
    }
    
    
  //Save all Tweet by 3 ways of body

    @PostMapping("/BodyInserters")
    public Mono<Tweet> createTweetsBodyInserters(@Valid @RequestBody Tweet tweet) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
        return webClient
                .post()
                .uri("/TweetProducer")
                .body(BodyInserters.fromValue(tweet))
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    @PostMapping("/syncBody")
    public Mono<Tweet> createTweetsSyncBody(@Valid @RequestBody Tweet tweet) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
        return webClient
                .post()
                .uri("/TweetProducer")
                .syncBody(tweet)
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    
    @PostMapping("/justBody")
    public Mono<Tweet> createTweetsBody(@Valid @RequestBody Tweet tweet) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
        return webClient
                .post()
                .uri("/TweetProducer")
                .body(Mono.just(tweet), Tweet.class)
                .retrieve()
                .bodyToMono(Tweet.class);
    }
    
    

    @GetMapping("/pathParam/{id}")
    public Mono<Tweet> getTweetById(@PathVariable(value = "id") String tweetId) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
        return webClient
                .get()
                .uri("/TweetProducer/pathParam/{id}", tweetId)
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }
    
    @GetMapping("/queryParam")
    public Mono<Tweet> getTweetByIdQueryParam(@RequestParam(value = "id") String tweetId) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/TweetProducer/byQueryParam")
                        .queryParam("id", tweetId)
                        .build())
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }
    
    
    @PutMapping("/update/{id}")
    public Mono<Tweet> updateTweet(@PathVariable(value = "id") String tweetId,
                                                   @Valid @RequestBody Tweet tweet) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
		
    	return webClient
                .post()
                .uri("/TweetProducer/update/{id}", tweetId)
                .body(Mono.just(tweet), Tweet.class)
        		.retrieve()
        		.bodyToMono(Tweet.class);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteTweet(@PathVariable(value = "id") String tweetId) {
        WebClient webClient = webClientBuilder.baseUrl("http://WebFlux-Producer").build();
    	return webClient
                .delete()
                .uri("/TweetProducer/delete/{id}", tweetId)
        		.retrieve()
        		.bodyToMono(Void.class);
    }




}
