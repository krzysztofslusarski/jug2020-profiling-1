package pl.britenet.profiling.demo.other;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {
    @GetMapping("/other/{i}")
    public String doOther(@PathVariable("i") int i) {
        return "OK";
    }
}
