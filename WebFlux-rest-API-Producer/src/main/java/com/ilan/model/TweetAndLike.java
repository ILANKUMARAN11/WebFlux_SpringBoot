package com.ilan.model;

import com.ilan.entity.Tweet;
import com.ilan.entity.TweetsLike;

public class TweetAndLike {

    Tweet tweet;

    TweetsLike tweetsLike;

    public TweetAndLike(){

    }

    public TweetAndLike(Tweet tweet, TweetsLike tweetsLike){
        this.tweet=tweet;
        this.tweetsLike = tweetsLike;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public TweetsLike getTweetsLike() {
        return tweetsLike;
    }

    public void setTweetsLike(TweetsLike tweetsLike) {
        this.tweetsLike = tweetsLike;
    }
}
