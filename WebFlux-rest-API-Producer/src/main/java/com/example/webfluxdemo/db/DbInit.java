package com.example.webfluxdemo.db;


import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.webfluxdemo.entity.Tweet;
import com.example.webfluxdemo.repository.TweetRepository;


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
        Tweet kiruthika = new Tweet("MOM","Ranjani","advise", "Dont talk too much");
        Tweet vela = new Tweet("ARUN","ILAN","job", "you will get a job");
        Tweet manoj = new Tweet("SARAVANAN","ILAN","back to india", "When r u back to india");
        

        List<Tweet> tweetList = Arrays.asList(ilan,gokul,kiruthika,vela,manoj);

        // Save to db
        this.tweetRepository.saveAll(tweetList).log().subscribe();
    }
}


