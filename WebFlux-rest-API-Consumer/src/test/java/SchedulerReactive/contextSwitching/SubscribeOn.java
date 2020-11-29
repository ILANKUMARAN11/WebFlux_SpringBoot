package SchedulerReactive.contextSwitching;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

public class SubscribeOn {


    @Test
    public void subscribeOnTop() {

        Flux.range(1, 2).log()
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    log("Map1", i * 1);
                    return i;
                })
                .map(i -> {
                    log("Map2", i * 2);
                    return i;
                })
                .subscribe();

    }

    @Test
    public void subscribeOnBottom() {

        Flux.range(1, 2).log()
                .map(i -> {
                    log("Map1", i * 1);
                    return i;
                })
                .map(i -> {
                    log("Map2", i * 2);
                    return i;
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

    }


    @Test
    public void subscribeOnMiddle() {

        Flux.range(1, 2).log()
                .map(i -> {
                    log("Map1", i * 1);
                    return i;
                })
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    log("Map2", i * 2);
                    return i;
                })
                .subscribe();

    }


    @Test
    public void multipleSubscribeOn() {

        Flux.range(1, 2).log()
                .subscribeOn(Schedulers.single())
                .map(i -> {
                    log("Map1", i * 1);
                    return i;
                })
                .subscribeOn(Schedulers.elastic())
                .map(i -> {
                    log("Map1", i * 2);
                    return i;
                })
                .subscribeOn(Schedulers.parallel())
                .map(i -> {
                    log("Map1", i * 3);
                    return i;
                })
                .subscribe();
    }

    @Test
    public void subscribeOnCase1() {
        Flux.range(1, 2).log()
                .publishOn(Schedulers.newParallel("Ilan"))
                .map(i -> {
                    log("Map1", i);
                    return i;
                })
                .subscribeOn(Schedulers.newParallel("UseLess"))
                .map(i -> {
                    log("Map2", i);
                    return i;
                })
                .publishOn(Schedulers.elastic())
                .map(i -> {
                    log("Map3", i);
                    return i;
                })
                .subscribe();
    }

    @Test
    public void subscribeOnCase2() {

        Flux.range(1, 2).log()
                .subscribeOn(Schedulers.single())
                .map(i -> {
                    log("Map1", i);
                    return i;
                })
                .publishOn(Schedulers.elastic())
                .map(i -> {
                    log("Map2", i);
                    return i;
                })
                .subscribeOn(Schedulers.parallel())
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
