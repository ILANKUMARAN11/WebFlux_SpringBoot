package Retry;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class RetryWithDelay {

    @Test
    void RetryWithDelay() throws InterruptedException {
        Flux.error(()->new RuntimeException("Ilan Exception")).log()
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)))
                .subscribe();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        sleep(30000);
    }

    @Test
    void BackOffDelay() throws InterruptedException {
        Flux.error(()->new RuntimeException("Ilan Exception")).log()
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))
                .subscribe();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        sleep(30000);
    }

//    @Test
//    void ExponentialBackOffDelay() throws InterruptedException {
//        Flux.error(()->new RuntimeException("Ilan Exception")).log()
//                .retry
//                .subscribe();
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        sleep(30000);
//    }
}
