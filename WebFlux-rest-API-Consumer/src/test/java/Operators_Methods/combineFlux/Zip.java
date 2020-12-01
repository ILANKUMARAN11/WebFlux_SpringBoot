package Operators_Methods.combineFlux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;

public class Zip {

    @Test
    @DisplayName("ZipWithDelay")
    public void zipWithDelay(){
        Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2=Flux.just(1,2,3).delayElements(Duration.ofSeconds(2));
        Flux<Character> flux3=Flux.just('W','X','Y','Z').delayElements(Duration.ofSeconds(1));

        Flux<Tuple3<String, Integer, Character>> combinedFlux = Flux.zip(flux1,flux2,flux3).log();

        StepVerifier.create(combinedFlux).
                expectNextCount(3).
                verifyComplete();
    }

    @Test
    @DisplayName("ZipWithMethod")
    public void zipWithMethod(){
        Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Flux<Integer> flux2=Flux.just(1,2,3).delayElements(Duration.ofSeconds(2));
        Flux<Character> flux3=Flux.just('W','X','Y','Z').delayElements(Duration.ofSeconds(1));

        Flux<Tuple2<Tuple2<String, Integer>, Character>> combinedFlux = flux1.zipWith(flux2).zipWith(flux3).
                                                                        log();

        StepVerifier.create(combinedFlux).
                expectNextCount(3).
                verifyComplete();
    }
}
