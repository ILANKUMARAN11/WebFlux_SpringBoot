package SchedulerReactive.contextSwitching;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RunOn {


    @Test
    public void publishOn() {

        int CPU_Core = Runtime.getRuntime().availableProcessors();

        Flux.range(1, 6)
                .parallel()
                .runOn(Schedulers.newParallel("Parallel"))
                .map(i -> i)
                .sequential()
                .subscribe();

    }

    private <T> void log(String operatorName, T element) {
        System.out.println("Operator::" + operatorName
                + ", Thread Name:: " + Thread.currentThread().getName()
                + ", element:: " + element);
    }
}
