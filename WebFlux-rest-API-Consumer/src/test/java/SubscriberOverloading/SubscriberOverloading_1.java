package SubscriberOverloading;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


public class SubscriberOverloading_1 {



    @Test
    @DisplayName("")
    public void monoSubscriberConsumer(){
        String name="Ilankumaran";
        Mono<String> mono=Mono.just(name).log();

        Consumer c= s-> System.out.println(s);

        mono.subscribe(c);
    }


    @Test
    public void monoSubscriberConsumerError(){

        Flux<Integer> mono=Flux.range(1,5)
                .log()
                .map(s->{ if(s==3)throw new RuntimeException("My Exception");
                    return s;});

        Consumer consumer= c-> System.out.println(c);
        Consumer<Throwable> throwable=t-> System.out.println(">>>>>>"+t.getMessage());
        Consumer<Throwable> throwable1=Throwable::printStackTrace;
        mono.subscribe(consumer,throwable);

    }

    @Test
    public void monoSubscriberConsumerRunnable(){
        String name="Ilankumaran";
        Mono<String> mono=Mono.just(name)
                .log();

        Consumer consumer= c-> System.out.println(c);
        Consumer<Throwable> throwable=t-> System.out.println(t.getMessage());
        Runnable runnable=()-> System.out.println("This is Runnable Interface");

        mono.subscribe(consumer,throwable,runnable);
    }

    @Test
    public void monoSubscriberConsumerSubscription(){
        List<String> abc= Arrays.asList(new String[]{"ABC","DEF","GHI","JKL","MNO","PQR","STU","VWX","YZ"});
        Flux<String> flux = Flux.fromIterable(abc).log();

        Consumer consumer= c-> System.out.println("Just Empty Consumer >>"+c);
        Consumer<Throwable> throwable=t-> System.out.println(t.getMessage());
        Runnable runnable=()-> System.out.println("This is Runnable Interface");

        Consumer<Subscription> subsRequest=c-> c.request(2);
        Consumer<Subscription> subsCancel=c-> c.cancel();
        flux.subscribe(consumer,throwable,runnable,subsRequest);
    }
}
