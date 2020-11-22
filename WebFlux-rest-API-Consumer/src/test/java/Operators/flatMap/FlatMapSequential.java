package Operators.flatMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.HashMap;

public class FlatMapSequential {

    @Test
    @DisplayName("FlatMapSequential Scheduler Example")
    public void squareNo(){
        Flux<String> stringFlux= Flux.range(1,10).
                window(2).
                flatMapSequential(identifiers-> identifiers.
                        flatMap(empId->Flux.just(getEmpName(empId))).subscribeOn(Schedulers.newParallel("P"))).
                subscribeOn(Schedulers.newParallel("P1")).
                log();


        StepVerifier.create(stringFlux)
                .expectNextCount(10)
                .verifyComplete();

        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Mocking this method makes DB call to fetch Emp Mark for his Emp ID
    public String getEmpName(int empId){
        HashMap<Integer,String> empData=new HashMap<Integer,String>();
        empData.put(1,"A");
        empData.put(2,"B");
        empData.put(3,"C");
        empData.put(4,"D");
        empData.put(5,"E");
        empData.put(6,"F");
        empData.put(7,"G");
        empData.put(8,"H");
        empData.put(9,"I");
        empData.put(10,"J");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return empData.get(empId);
    }
}
