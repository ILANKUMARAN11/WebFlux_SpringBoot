package ExceptionHandling;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

public class DoFinally {

    /*
      # This is similar to Finally block as we have in Core java.
      # doFinally give U the SignalType based on the publisher execution where
            U can perform your logic based upon the SignalType.
      # If successful completion SignalType will be ON_COMPLETE.
      # If ERROR SignalType will be ON_ERROR.
      # If subscription cancel SignalType will be CANCEL.
     */

    @Test
    void doFinallyComplete(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                doFinally(e->{
                    SignalType type=e;
                    if(type==type.ON_NEXT){
                        System.out.println(">>>>> ON-NEXT");
                    } else if(type==type.ON_COMPLETE){
                        System.out.println(">>>>> ON-COMPLETE");
                    } else if(type==type.ON_ERROR){
                        System.out.println(">>>>> ON-ERROR");
                    } else if(type==type.CANCEL){
                        System.out.println(">>>>> ON-CANCEL");
                    }else {
                        System.out.println("#####"+type+"####");
                    }
                }).log();

        integerFlux.subscribe();
    }

    @Test
    void doFinallyError(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                concatWith(Flux.error(ArithmeticException::new)).
                doFinally(e->{
                    SignalType type=e;
                    if(type==type.ON_COMPLETE){
                        System.out.println(">>>>> ON-COMPLETE");
                    } else if(type==type.ON_ERROR){
                        System.out.println(">>>>> ON-ERROR");
                    } else if(type==type.CANCEL){
                        System.out.println(">>>>> ON-CANCEL");
                    }else {
                        System.out.println("#####"+type+"####");
                    }
                }).log();

        integerFlux.subscribe();
    }

    @Test
    void doFinallyCancel(){
        Flux<String> integerFlux=Flux.just("A","B","C").
                doFinally(e->{
                    SignalType type=e;
                    if(type==type.ON_COMPLETE){
                        System.out.println(">>>>> ON-COMPLETE");
                    } else if(type==type.ON_ERROR){
                        System.out.println(">>>>> ON-ERROR");
                    } else if(type==type.CANCEL){
                        System.out.println(">>>>> ON-CANCEL");
                    }else {
                        System.out.println("#####"+type+"####");
                    }
                }).log();

        integerFlux.subscribe(e->{System.out.print(e);},ex->{},()->{},Subscription::cancel);
    }
}
