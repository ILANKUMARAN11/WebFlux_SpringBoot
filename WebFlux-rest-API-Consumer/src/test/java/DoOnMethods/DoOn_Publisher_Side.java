package DoOnMethods;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;


//Project Reactor Essentials 06 - Mono doOnSubscribe, doOnRequest, doOnNext, doOnSuccess
public class DoOn_Publisher_Side {


    @Test
    public void monoDoOn(){
        Mono<String> mono= Mono.just("Test").log()
                        .doOnSubscribe(c-> System.out.println("when Subscriber Here>>>"+c))
                        .doOnRequest(c-> System.out.println("When request Here>>>"+c))
                        .doOnNext(c-> System.out.println("When Next Here>>>"+c))
                        .doOnSuccess(c-> System.out.println("When do on Success>>>"+c));

        mono.subscribe();
    }

}
