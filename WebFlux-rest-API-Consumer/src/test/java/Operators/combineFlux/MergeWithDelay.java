package Operators.combineFlux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class MergeWithDelay {



    @Test
    @DisplayName("MergeWithDelay")
    public void mergeWithDelay(){
        Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Flux<String> flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
        Flux<String> flux3=Flux.just("G","H","I","J").delayElements(Duration.ofSeconds(1));

        Flux<String> combinedFlux = Flux.merge(flux1,flux2,flux3).log();

        StepVerifier.create(combinedFlux).
                expectNextCount(10).
                verifyComplete();
    }
}
