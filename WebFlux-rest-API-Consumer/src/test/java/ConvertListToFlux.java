import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class ConvertListToFlux {

    @Test
    public void baseSubscriber() {

        List<String> abc= Arrays.asList(new String[]{"ABC","DEF","GHI"});

        Flux<String> flux = Flux.fromIterable(abc);
    }
}
