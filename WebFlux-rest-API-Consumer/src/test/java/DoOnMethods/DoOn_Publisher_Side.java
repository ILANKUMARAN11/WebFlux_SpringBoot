package DoOnMethods;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


//Project Reactor Essentials 06 - Mono doOnSubscribe, doOnRequest, doOnNext, doOnSuccess
public class DoOn_Publisher_Side {


    @Test
    public void monoDoOn(){
        Mono<String> mono= Mono.just("Test").log()
                        .doOnSubscribe(c-> System.out.println("when Subscribed>>>"+c))
                        .doOnCancel(()-> System.out.println("When on Cancel"))
                        .doOnRequest(c-> System.out.println("When Request>>>"+c))
                        .doOnNext(c-> System.out.println("When Next>>>"+c))
                        .doOnError(throwable->System.out.println("When Error>>>"+throwable))
                        .doOnSuccess(c-> System.out.println("When Success>>>"+c));

        StepVerifier.create(mono)
                .expectNext("Test")
                .verifyComplete();
    }

    @Test
    public void DoOnError(){
        Flux<Object> error= Flux.error(RuntimeException::new).log()
                .doOnError(throwable->System.out.println("When Error>>>"+throwable))
                .doOnComplete(() -> System.out.println("Complete"));

        StepVerifier.create(error)
                .expectError(RuntimeException.class)
                .verify();
    }

}
