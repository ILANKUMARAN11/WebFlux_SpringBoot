package operators.combineFlux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Concat {


    /*
        # Concat is used to combine two or more flux together.
        # Concat waits for each flux(publisher) to emit all of it element to complete,
           then it combine to next one.
        # Since Concat waits for flux to complete emitting the element,
           the order is maintained after the combine.
     */
    @Test
    @DisplayName("Combine Two Fux")
    public void combineFlux(){
        Flux<String> flux1=Flux.just("A","B","C");
        Flux<String> flux2=Flux.just("D","E","F");
        Flux<String> flux3=Flux.just("G","H","I","J");

        Flux<String> combinedFlux = Flux.concat(flux1,flux2,flux3).log();

        StepVerifier.create(combinedFlux).
                expectNext(new String[]{"A","B","C","D","E","F","G","H","I","J"}).
                verifyComplete();
    }

    /*
        # Concat waits for each flux(publisher) to emit all of it element to complete,
           then it combine to next one.
        # Since Concat waits for flux to complete emitting the element,
           the order is maintained after the combine.
     */
    @Test
    @DisplayName("ConcatWithDelay")
    public void concatWithDelay(){
        Flux<String> flux1=Flux.just("A","B","C").delayElements(Duration.ofSeconds(1));
        Flux<String> flux2=Flux.just("D","E","F").delayElements(Duration.ofSeconds(1));
        Flux<String> flux3=Flux.just("G","H","I","J").delayElements(Duration.ofSeconds(1));

        Flux<String> combinedFlux = Flux.concat(flux1,flux2,flux3).log();

        StepVerifier.create(combinedFlux).
                expectNext(new String[]{"A","B","C","D","E","F","G","H","I","J"}).
                verifyComplete();
    }


}
