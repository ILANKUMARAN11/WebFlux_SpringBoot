package ConnectableFlux;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class HotPublisher {

    Flux<String> stringFlux=Flux.just("A","B","C","D")
            .delayElements(Duration.ofSeconds(1));

    @Test
    public void HotPublisher() throws InterruptedException {

        //Below Publish method convert the Flux to ConnectableFlux
        ConnectableFlux<String>  hotPublisher=stringFlux.publish();
        hotPublisher.connect();

        hotPublisher.subscribe(element-> System.err.println("1st_Subscriber>>>>>>>"+element));
        sleep(2000);

        hotPublisher.subscribe(element-> System.out.println("2nd_Subscriber>>>>>>>"+element));
        sleep(4000);
    }


    @DisplayName("Hot Publisher Expecting at least 2 Subscriber ")
    @Test
    public void HotPublisherExpectingSubscriber() throws InterruptedException {

        //Below Publish method convert the Flux to ConnectableFlux
        ConnectableFlux<String>  hotPublisher=stringFlux.publish();

        /*Below autoConnect method take an input as
            number of minimum subscriber to be available to emmit the elements.
              (i.e) If we don't have at least 2 subscribers then Publisher won't emmit it's element.
         */
        Flux<String>   expectingSubscriber=hotPublisher.autoConnect(2);

        expectingSubscriber.subscribe(element-> System.err.println("1st_Subscriber>>>>>>>"+element));
        sleep(2000);

        expectingSubscriber.subscribe(element-> System.out.println("2nd_Subscriber>>>>>>>"+element));
        sleep(4000);

        expectingSubscriber.subscribe(element-> System.out.println("3nd_Subscriber>>>>>>>"+element));
        sleep(4000);
    }
}
