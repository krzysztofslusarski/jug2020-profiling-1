package pl.britenet.profiling.demo.math;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MathController {
    private long last = 100_000_000;

    @GetMapping("/math")
    HttpEntity<Long> calculateNext() {
        long result = 1;
        for (long i = 0; i < last; i++) {
            result *= i;
            if (result == 0) {
                result = last;
            }
        }
        last++;
        return new HttpEntity<>(result);
    }
}
