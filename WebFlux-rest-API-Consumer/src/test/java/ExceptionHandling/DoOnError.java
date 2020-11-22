package ExceptionHandling;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class DoOnError {

    /**
     # If U want to do something when exception occurs, then doOnError is the best option.
     # It’ll catch and rethrow the exception.
     # Here onComplete method will not be executed.
     # Example: when you want to do the LOG an error.
     # Example: when you want to do some logical operation.
     */
    @Test
    void onErrorReturn(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(new RuntimeException("Some Exception"))).
                concatWith(Flux.just("D")).
                doOnError(ex -> {
                    System.out.println("ERROR::: "+ex.getMessage());
                }).log();

        StepVerifier.create(integerFlux).
                expectNext("A","B","C").
                verifyErrorMessage("Some Exception");
    }
}
