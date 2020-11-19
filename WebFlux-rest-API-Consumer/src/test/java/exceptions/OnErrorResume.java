package exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

public class OnErrorResume {

    /**
     # Execute an alternative path with a fallback method.
     # Compute a dynamic fallback value.
     # Catch, wrap, and re-throw an error e.g. as a custom business exception.
     # If U want to return fallBack value(Flux/Mono) when exception occurs, then onErrorResume
     is the best option.
     # onErrorResume method returns fallBack values that is Flux/Mono when exception occurs.
     # Here onComplete method will be executed unless we re-throw the exception.
     */

    private Flux<String> sayHelloFallback(){
        return Flux.just("Say","Hello","fallBack");
    }

    @Test
    @DisplayName("Fallback method")
    void onErrorResume(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(new RuntimeException("Some Exception"))).
                concatWith(Flux.just("D")).
                onErrorResume(ex->sayHelloFallback()).log();

        StepVerifier.create(integerFlux).
                expectNext("A","B","C","Say","Hello","fallBack").
                verifyComplete();
    }

    /*
     # Here, we're returning a String consisting of the
            dynamically obtained error message appended to the string “Error” whenever
            publisher throws the Exception.
     */
    @Test
    @DisplayName("compute Dynamic Value")
    void computeDynamicValue(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(new RuntimeException("Some Exception"))).
                concatWith(Flux.just("D")).
                onErrorResume(ex->Flux.just("Error Message :::"+ex.getMessage())).log();

        StepVerifier.create(integerFlux).
                expectNext("A","B","C", "Error Message :::"+"Some Exception").
                verifyComplete();
    }


    /*
     # Here, we're returning a String consisting of the
            dynamically obtained error message appended to the string “Error” whenever
            publisher throws the Exception.
     */
    @Test
    @DisplayName("catch, wrap, and re-throw an error")
    void reThrowError(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(new RuntimeException("Some Exception"))).
                concatWith(Flux.just("D")).
                onErrorResume(ex->Flux.error(new RuntimeException("re-Throw Exception"))).log();

        StepVerifier.create(integerFlux).
                expectNext("A","B","C").
                expectErrorMessage("re-Throw Exception").
                verify();
    }
}
