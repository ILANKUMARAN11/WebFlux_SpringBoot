package com.ilan;

import java.util.Collections;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ilan.controller.ProducerTweetController;
import com.ilan.entity.Tweet;
import com.ilan.repository.TweetRepository;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {ProducerTweetController.class})
//@Import(EmployeeService.class)
public class WebfluxDemoApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
    TweetRepository tweetRepository;

	@Test
	public void testCreateTweet() {
		Tweet tweet = new Tweet("ILAN","Ranjani","Family issues", "Keep within us");

		webTestClient.post().uri("/tweets")
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(tweet), Tweet.class)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.text").isEqualTo("Keep within us");
	}

	@Test
    public void testGetAllTweets() {
	    webTestClient.get().uri("/tweets")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Tweet.class);
    }

    @Test
    public void testGetSingleTweet() {
        Tweet tweet = tweetRepository.save(new Tweet("ILAN","Ranjani","Family issues", "Keep within us")).block();

        webTestClient.get()
                .uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testUpdateTweet() {
        Tweet tweet = tweetRepository.save(new Tweet("ILAN","Ranjani","Family issues", "Keep within us")).block();

        Tweet newTweetData = new Tweet("JOJO","BEBE",""
        		+ " issues", "Updated Keep within us");

        webTestClient.put()
                .uri("/tweets/{id}", Collections.singletonMap("id", tweet.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(newTweetData), Tweet.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.text").isEqualTo("Updated Keep within us");
    }

    @Test
    public void testDeleteTweet() {
	    Tweet tweet = tweetRepository.save(new Tweet("ILAN","Ranjani","Family issues", "Keep within us")).block();

	    webTestClient.delete()
                .uri("/tweets/{id}", Collections.singletonMap("id",  tweet.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
