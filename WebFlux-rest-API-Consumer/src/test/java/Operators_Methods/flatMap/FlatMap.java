package Operators_Methods.flatMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.HashMap;


public class FlatMap {

    @Test
    @DisplayName("FlatMap Async Example")
    public void squareNo(){
        Flux<String> stringFlux= Flux.range(1,10).
                flatMap(e-> Flux.just(getEmpName(e))). //FlatMap to make DB or External service call that returns Flux/Mono .
                log();

        Long start = System.currentTimeMillis();
        StepVerifier.create(stringFlux)
                .expectNext(new String[]{"A","B","C","D","E","F","G","H","I","J"})
                .verifyComplete();
        Long finish = System.currentTimeMillis();

        long timeElapsed = finish-start;
        System.out.println("Duration of Completion in MILLI_SECONDS >>>>"+timeElapsed);
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
