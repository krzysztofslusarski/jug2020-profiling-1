package pl.britenet.profiling.demo.memory;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecondMemoryController {
    private static volatile long a;
    private static volatile long b;

    private long count = 10_000_000;

    @GetMapping("/memory/second/a")
    public HttpEntity<Long> getA() {
        for (long i = 0; i < count; i++) {
            a++;
        }
        return new HttpEntity<>(a);
    }

    @GetMapping("/memory/second/b")
    public HttpEntity<Long> getB() {
        for (long i = 0; i < count; i++) {
            b++;
        }
        return new HttpEntity<>(b);
    }

    public void beforeJIT() {
        count = 10_000;
    }

    public void afterJIT() {
        count = 10_000_000;
    }
}
