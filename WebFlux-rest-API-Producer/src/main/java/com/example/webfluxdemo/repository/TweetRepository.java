package com.example.webfluxdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.webfluxdemo.entity.Tweet;


@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {

}
