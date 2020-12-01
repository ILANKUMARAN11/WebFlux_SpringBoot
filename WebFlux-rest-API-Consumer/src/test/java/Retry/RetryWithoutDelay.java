package Retry;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static java.lang.Thread.sleep;

public class RetryWithoutDelay {

    @Test
    void RetryWithoutDelay() throws InterruptedException {
        Flux.error(()->new RuntimeException("Ilan Exception")).log()
                .retry(3)
                .subscribe();

        sleep(10000);
    }

}
