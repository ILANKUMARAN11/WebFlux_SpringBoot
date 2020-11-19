package backPressure;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class BackPressure_Ugly_1 {


    @Test
    public void uglyBackPressure(){

        Flux<Integer> flux=Flux.range(100,200).log();

        Subscriber<Integer> subscriber=new Subscriber<Integer>() {
            private Subscription subscription;
            private int count = 0;
            private final int requestCount=2;


            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                subscription.request(requestCount);
            }

            @Override
            public void onNext(Integer dataReceived) {
                System.out.println(">>>>>>>>>>>Data emitted from publisher >>>>"+dataReceived);
                count++;
                if (count >= requestCount) {
                    count = 0;
                    subscription.request(requestCount);
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("If any logic when ERROR is happened, then do ur logic here"+t.getMessage());
            }
            @Override
            public void onComplete() {
                System.out.println("If any logic after publisher successfully emitted all the data, then do ur logic here");
            }


        };

        flux.subscribe(subscriber);
    }


}
