package pl.britenet.profiling.demo.scheduled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.Diagnostics;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class ScheduledJob {
    @Scheduled(fixedRate = 1L)
    public void doInScheduled() {
        Diagnostics.findDeadlock();
    }
}
