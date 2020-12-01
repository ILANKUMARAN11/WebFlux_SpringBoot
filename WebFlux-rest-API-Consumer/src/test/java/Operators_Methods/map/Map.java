package Operators_Methods.map;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Map {


    @Test
    public void lengthOfString(){
        Flux<Integer> stringFlux= Flux.just("Australia","CANADA","NETHERLAND","AUSTRIA").
                map(e->e.length()).log();

        StepVerifier.create(stringFlux)
                .expectNext(new Integer[]{9,6,10,7})
                .verifyComplete();
    }

    @Test
    public void squareNo(){
        Flux<Integer> stringFlux= Flux.range(1,5).
                map(e->e*e).log();

        StepVerifier.create(stringFlux)
                .expectNext(new Integer[]{1,4,9,16,25})
                .verifyComplete();
    }
}
