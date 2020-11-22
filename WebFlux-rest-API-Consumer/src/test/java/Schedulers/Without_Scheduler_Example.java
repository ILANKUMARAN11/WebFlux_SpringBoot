package Schedulers;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class Without_Scheduler_Example {

    @Test
    public void fluxDoOn(){

         Flux<String> flux = Flux
                .range(1, 2)
                .map(i -> 10 + i)
                .map(i -> "value " + i).log();

        new Thread(() -> flux.subscribe(System.out::println));


    }
}
