package Operators;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Filter {

    @Test
    public void filterGtEq(){
        Flux<Integer> integerFlux= Flux.range(1,10).
                filter(e->e>=5).log();

        StepVerifier.create(integerFlux)
                    .expectNext(new Integer[]{5,6,7,8,9,10})
                    .verifyComplete();
    }


    @Test
    public void filterNameStarts(){
        Flux<String> stringFlux= Flux.just("Australia","CANADA","NETHERLAND","AUSTRIA").
                filter(e->e.startsWith("N")).log();

        StepVerifier.create(stringFlux)
                .expectNext("NETHERLAND")
                .verifyComplete();
    }

}
