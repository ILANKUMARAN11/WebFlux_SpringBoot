package exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class OnErrorMap {

    /*
     # Sometimes we need to provide custom exceptions in order to be more clear about things,
        then onErrorMap is the best Option to throw Custom exception.
     # Let's say U got an Exception. Based upon the exception U want to throw Custom Exception,
        then onErrorMap is the best option.
     # It’ll catch and rethrow the exception.
     # Here onComplete method will not be executed.
     */

    @Test
    @DisplayName("catch, wrap, and re-throw an error")
    void reThrowError(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(new ArithmeticException("Arithmetic Exception"))).
                concatWith(Flux.just("D")).
                onErrorResume(ex->{ if(ex.getMessage().equals("Arithmetic Exception")) {
                    return Flux.error(new RuntimeException("Logical Exception"));
                }else{
                    return Flux.error(new RuntimeException("re-Throw Exception"));
                }});

        StepVerifier.create(integerFlux.log()).
                expectNext("A","B","C").
                expectErrorMessage("Logical Exception").
                verify();
    }

}
