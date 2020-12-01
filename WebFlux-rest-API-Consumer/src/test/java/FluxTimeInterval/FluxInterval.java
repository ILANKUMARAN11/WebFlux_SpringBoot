package FluxTimeInterval;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
     => Consider the situation where u want the
            publisher to emits items every specified Interval of time.
     => Our goal is to create Flux for (data1, data2, data3, …)
                        that emits items every specified Interval of time.
 */
public class FluxInterval {


    @Test
    void fluxSubscribeInterval() throws InterruptedException {

        Flux<String> intervalFlux=this.createInterval().log();

        intervalFlux.subscribe();

        Thread.sleep(3000);

    }

    private Flux<String> createInterval(){

        return Flux
                .interval(Duration.ofMillis(100))
                .map(tick -> {
                    String fluxString="A :"+tick.intValue();
                    return fluxString;
                }).take(10);
    }
}

