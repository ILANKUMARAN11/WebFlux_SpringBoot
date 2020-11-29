package SchedulerReactive.contextSwitching;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {

    @Test
    public void subscribeOn() {
        Scheduler scheduler = Schedulers.newSingle("SingleThread");

        Flux.range(1, 2).log()
                .map(i -> {
                    log("Map1", i);
                    return i;
                })
                .subscribeOn(scheduler)
                .map(i -> {
                    log("Map2", i);
                    return i;
                })
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    log("Map3", i);
                    return i;
                })
                .subscribe();
    }

    private <T> void log(String operatorName, T element) {
        System.out.println("Operator::" + operatorName
                + ", Thread Name:: " + Thread.currentThread().getName()
                + ", element:: " + element);
    }
}
