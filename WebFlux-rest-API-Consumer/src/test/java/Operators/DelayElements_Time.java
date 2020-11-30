package Operators;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class DelayElements_Time {

    Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(2)).log();

    Flux<Integer> delayFlux = Flux.range(1, 20)
            .delayElements(Duration.ofSeconds(2))
            .log();

    @Test
    void withVirtualTime() throws InterruptedException {
        StepVerifier.withVirtualTime(() -> delayFlux).expectNextCount(20).
                verifyComplete();

        sleep(10000);
    }


    @Test
    void testDelay() throws InterruptedException {
        StepVerifier.create(delayFlux).expectNextCount(20).
                verifyComplete();

        sleep(10000);
    }
}
