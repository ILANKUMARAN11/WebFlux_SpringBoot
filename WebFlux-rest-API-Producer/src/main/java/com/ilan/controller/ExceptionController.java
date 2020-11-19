package com.ilan.controller;


import com.ilan.exception.model.TweetNotFoundException;
import com.ilan.repository.TweetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/tweets")
public class ExceptionController {

    Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/onErrorReturn")
    public Flux<Integer> onErrorReturn() {
        return Flux.just(1,2,2,2,0,6)
                .map(x->100/x)
                .onErrorReturn(Integer.MAX_VALUE);
    }

    @GetMapping("/onErrorResume")
    public Flux<Integer> onErrorResume() {
        return Flux.just(1,2,2,2,0,6)
                .map(x->100/x)
                .onErrorResume(e->Flux.range(1,3));
    }

    @GetMapping("/onErrorMap")
    public Flux<Integer> onErrorMap() {
        return Flux.just(1,2,2,2,0,6)
                .map(x->100/x)
                .onErrorMap(e->new TweetNotFoundException(HttpStatus.NOT_FOUND,"TEST"));
    }


    @GetMapping("/doOnError")
    public Flux<Integer> doOnError() {
        return Flux.just(1,2,2,2,0,6)
                .map(x->100/x)
                .doOnError(e->log.info("Number can't be Divided by ZERO",new TweetNotFoundException(HttpStatus.NOT_FOUND,"TEST")));
    }

}
