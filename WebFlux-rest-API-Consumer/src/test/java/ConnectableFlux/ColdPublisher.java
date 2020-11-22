package ConnectableFlux;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class ColdPublisher {


    @Test
    public void ColdPublisher() throws InterruptedException {

       Flux<Integer> integerFlux=Flux.range(1,3).log()
                .delayElements(Duration.ofSeconds(1));

        integerFlux.subscribe(element-> System.err.println("1st_Subscriber>>>>>>>"+element));

        sleep(10000);

        integerFlux.subscribe(element-> System.out.println("2nd_Subscriber>>>>>>>"+element));

        sleep(50000);
    }
}
