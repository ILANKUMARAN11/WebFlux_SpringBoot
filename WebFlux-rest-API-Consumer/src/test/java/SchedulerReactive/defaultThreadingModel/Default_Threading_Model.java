package SchedulerReactive.defaultThreadingModel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Default_Threading_Model {

    @Test
    @DisplayName("Default Threading Model")
    public void defaultThreadModel(){

         Flux.range(1, 2)
                .map(i -> {log("map",i);
                return i;})
                 .log()
                 .subscribe();
    }

    @Test
    @DisplayName("delayElements Threading Model")
    public void delayElementsThreadModel() throws InterruptedException {

        Flux.range(1, 3)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> {log("map",i);
                    return i;})
                .log()
                .subscribe();

        Thread.sleep(3000);
    }


    private <T> void log(String operatorName, T element) {
        System.out.println("Operator::" + operatorName
                + ", Thread Name:: " + Thread.currentThread().getName()
                + ", element:: " + element);
    }
}
