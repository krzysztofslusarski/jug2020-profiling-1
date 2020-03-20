package pl.britenet.profiling.demo;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.britenet.profiling.demo.memory.FirstMemoryController;
import pl.britenet.profiling.demo.memory.SecondMemoryController;
import pl.britenet.profiling.demo.other.OtherController;
import pl.britenet.profiling.demo.stat.StatController;

@Slf4j
@Component
@RequiredArgsConstructor
class Jitter {
    private final OtherController otherController;
    private final StatController statController;
    private final FirstMemoryController firstMemoryController;
    private final SecondMemoryController secondMemoryController;

    @PostConstruct
    void jitIt() {
        log.info("Starting jitter");
        MagicNumbersCreatorImpl.beforeJIT();
        firstMemoryController.beforeJIT();;
        secondMemoryController.beforeJIT();;
        for (int i = 0; i < 15_000; i++) {
            if (i % 100 == 0) {
                log.info(i + "");
            }
            otherController.doOther(i);
            statController.get();
            RequestIdGenerator.generate();
            firstMemoryController.get();
            secondMemoryController.getA();
            secondMemoryController.getB();
        }
        MagicNumbersCreatorImpl.afterJIT();
        firstMemoryController.afterJIT();;
        secondMemoryController.afterJIT();;
        log.info("Ending jitter");
    }
}
