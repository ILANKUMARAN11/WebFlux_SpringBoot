package PusblisherDoOn;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;

//Project Reactor Essentials 06 - Mono doOnSubscribe, doOnRequest, doOnNext, doOnSuccess
public class PublisherDoOn_2 {


    @Test
    public void monoDoOn(){
        Mono<String> mono= Mono.just("Test").log()
                        .doOnSubscribe(c-> System.out.println("when Subscriber Here>>>"+c))
                        .doOnRequest(c-> System.out.println("When request Here>>>"+c))
                        .doOnNext(c-> System.out.println("When Next Here>>>"+c))
                        .doOnSuccess(c-> System.out.println("When do on Success>>>"+c));

        mono.subscribe();
    }


    @Test
    public void fluxDoOn(){

        Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);

        final Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .subscribeOn(s)
                .map(i -> "value " + i).log();

        new Thread(() -> flux.subscribe(System.out::println));


//
//        Flux<Integer> mono= Flux.range(1,10).log()
//                .doOnSubscribe(c-> System.out.println("when Subscriber Here>>>"))
//                .doOnRequest(c-> System.out.println("When request Here>>>"))
//                .doOnNext(c-> System.out.println("When Next Here>>>"))
//                .doOnComplete(()-> System.out.println("On complete" ));
//
//        mono.subscribe();
    }
}
