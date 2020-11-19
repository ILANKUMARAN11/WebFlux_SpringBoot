package backPressure;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class BackPressure_BaseSubscriber_NotSoUgly_2 {


    @Test
    public void baseSubscriber(){

        Flux<Integer> flux=Flux.range(100,200).log();

        BaseSubscriber<Integer> baseSubscriber=new BaseSubscriber<Integer>() {
            private int count = 0;
            private final int requestCount=2;

            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(requestCount);
            }

            @Override
            protected void hookOnNext(Integer dataReceived) {
                System.out.println(">>>>>>>>>>>Data emitted from publisher >>>>"+dataReceived);
                count++;
                if (count >= requestCount) {
                    count = 0;
                    request(requestCount);
                }
            }

            @Override
            protected void hookOnError(Throwable t) {
                System.out.println("If any logic when ERROR is happened, then do ur logic here"+t.getMessage());
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("If any logic after publisher successfully emitted all the data, then do ur logic here");
            }
        };

        flux.subscribe(baseSubscriber);
    }

}
