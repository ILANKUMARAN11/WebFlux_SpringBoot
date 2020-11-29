package SchedulerReactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RunOn {


    @Test
    public void publishOn() {

        int CPU_Core = Runtime.getRuntime().availableProcessors();

        Flux.range(1, 7)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(i -> {
                    log("Map", i);
                    return i;
                })
                .sequential()
                .subscribe();

    }

    private <T>  void log(String operatorName, T element){
        System.out.println("Operator::"+operatorName
                +", element:: "+element
                +", Thread Name:: "+Thread.currentThread().getName());
    }
}
