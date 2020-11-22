package ConnectableFlux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class HotPublisher {

    @Test
    public void HotPublisher() throws InterruptedException {

        Flux<String> stringFlux=Flux.just("A","B","C","D")
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<String>  hotPublisher=stringFlux.publish();
        hotPublisher.connect();

        hotPublisher.subscribe(element-> System.err.println("1st_Subscriber>>>>>>>"+element));
        sleep(2000);

        hotPublisher.subscribe(element-> System.out.println("2nd_Subscriber>>>>>>>"+element));
        sleep(4000);
    }
}
