package operators;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class Take {


    @Test
    void take(){
        Flux<Integer> integerFlux=Flux.range(1,5).
               log().take(2);

        StepVerifier.create(integerFlux).expectNext(1,2).
                verifyComplete();
    }

    @Test
    void window() throws InterruptedException {
        Flux<Integer> integerFlux=Flux.range(1,20).
                window(5).
                delayElements(Duration.ofSeconds(2)).
                flatMap(e->e).
                log();

        StepVerifier.create(integerFlux).expectNextCount(20).
                verifyComplete();

        sleep(10000);
    }

}
