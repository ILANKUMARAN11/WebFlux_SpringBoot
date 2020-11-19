package com.ilan.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.ilan.entity.Tweet;

import reactor.core.publisher.Flux;


@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {


    Flux<Tweet> findBySubject(String subject);

    Flux<Tweet> findTweetBySenderAndSubjectLike(String sender,String subject);

}
