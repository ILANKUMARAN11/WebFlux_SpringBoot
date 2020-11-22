package BackPressure;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

/*
        => Backpressure is always on consumer side. That means Consumer says
            the producer to emmit only the requested amount of data.

        => Backpressure can also be set on producer side using LimitRate.
 */
public class BackPressure_LimitRate_3 {

    @Test
    public void baseSubscriber(){

        int sendBackpressureCount=3;
        Flux<Integer> flux=fluxProducerLimitRate(sendBackpressureCount);
        flux.subscribe();

    }

    public Flux<Integer> fluxProducerLimitRate(int requestCount){

        Flux<Integer> fluxLimitRate=Flux.range(100,200).
                log().
                limitRate(requestCount);

        return  fluxLimitRate;
    }


}
