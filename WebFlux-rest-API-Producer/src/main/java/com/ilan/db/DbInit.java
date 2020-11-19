package com.ilan.db;


import java.util.Arrays;
import java.util.List;

import com.ilan.repository.TweetRepository;
import org.reactivestreams.Subscription;
import org.reactivestreams.Subscriber;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.ilan.entity.Tweet;
import reactor.core.publisher.Flux;


@Service
public class DbInit implements CommandLineRunner {
	
    private TweetRepository tweetRepository;
   

    public DbInit(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void run(String... args) {
        // Delete all
        this.tweetRepository.deleteAll().log().subscribe();

        // Crete users
        Tweet ilan = new Tweet("ILAN","Ranjani","Family issues", "Keep within us");
        Tweet gokul = new Tweet("DAD","ILAN","Advise", "Keep your voice low");
        Tweet kiruthika = new Tweet("MOM","Ranjani","Advise", "Dont talk too much");
        Tweet vela = new Tweet("ARUN","ILAN","job", "you will get a job");
        Tweet manoj = new Tweet("SARAVANAN","ILAN","back to india", "When r u back to india");
        

        List<Tweet> tweetList = Arrays.asList(ilan,gokul,kiruthika,vela,manoj);

        //this.tweetRepository.saveAll(tweetList).subscribe();

        // Save to db
        this.tweetRepository.saveAll(tweetList).subscribe(new Subscriber<Tweet>() {
            private Subscription subscription;
            private Integer count = 0;

            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(2);
            }

            @Override
            public void onNext(Tweet t) {
                count++;
                if (count >= 2) {
                    count = 0;
                    subscription.request(2);
                }
            }

            @Override
            public void onError(Throwable t) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onComplete() {
                System.out.println();
            }
        });
    }
}


