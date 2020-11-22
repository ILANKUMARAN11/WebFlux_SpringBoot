package ExceptionHandling;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OnErrorReturn {

    /**
     # If U want to return a static default value(fallBack value) when exception occurs, then onErrorReturn
        is the best option.
     # onErrorReturn method returns fallBack value that is single value when exception occurs.
     # Here onComplete method will be executed.
     */

    @Test
    void onErrorReturn(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                                    concatWith(Flux.error(new RuntimeException("Some Exception"))).
                                    concatWith(Flux.just("D")).
                                    onErrorReturn("Some default value").log();

        StepVerifier.create(integerFlux).
                expectNext("A","B","C","Some default value").
                verifyComplete();
    }

}
