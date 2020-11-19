package com.ilan.controller;


import com.ilan.entity.TweetsLike;
import com.ilan.entity.Tweet;
import com.ilan.model.TweetAndLike;
import com.ilan.repository.TweetLikeRep;
import com.ilan.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tweets")
public class CodingTryOut {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TweetLikeRep tweetLikeRep;

    @GetMapping("/Test")
    Flux<Tweet> getTweet() {
       return  tweetRepository.findBySubject("Advise")
                .log()
                .map(p->p);
    }

    @GetMapping("/findBySenderAndSubjectLike")
    Flux<Tweet> findBySenderAndSubjectLike() {
        return tweetRepository.findTweetBySenderAndSubjectLike("DAD", "Advi").log();
    }


    @PostMapping("/Test")
    Mono<TweetAndLike> getTweet(@RequestBody TweetAndLike tweetAndLike) {

        Tweet twt= tweetAndLike.getTweet();
        TweetsLike twtLik= tweetAndLike.getTweetsLike();

        return tweetRepository.save(twt).log()
                .flatMap(t->{
                    Tweet tw=t;
                    twtLik.setfId(tw.getId());

                    Mono<TweetAndLike> yy= tweetLikeRep.save(twtLik).log()
                            .map(twL->{
                                TweetAndLike tws=new TweetAndLike();
                                tws.setTweet(tw);
                                tws.setTweetsLike(twL);
                                return tws;
                            });

                    return  yy;

                });


    }


    @GetMapping("/getTweetByMono/{id}")
    public Mono<Tweet> getTweetByMono(@PathVariable(value = "id") String tweetId) {
        return tweetRepository.findById(tweetId).log()
                //.map(p->p)
                .flatMap(p->Mono.just(p));
    }

    @GetMapping("/getTweetByResponseEntity/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetByResponseEntity(@PathVariable(value = "id") String tweetId) {

        tweetRepository.findById(tweetId).concatWith(tweetRepository.findAll());
        return tweetRepository.findById(tweetId).log()
                .map(p-> new ResponseEntity(p, HttpStatus.OK));
    }


}
