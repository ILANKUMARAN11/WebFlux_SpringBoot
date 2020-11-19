package com.ilan.repository;

import com.ilan.entity.TweetsLike;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TweetLikeRep extends ReactiveMongoRepository<TweetsLike, String> {

}