package pl.britenet.profiling.demo.stat;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.britenet.profiling.demo.MagicNumbersCreator;
import pl.britenet.profiling.demo.MagicNumbersCreatorImpl;

@RestController
public class StatController {
    private static List<Integer> percentiles = List.of(50, 60, 70, 80, 90, 99, 100);

    private static MagicNumbersCreator magicNumbersCreator = new MagicNumbersCreatorImpl();

    private static List<Double> generateReport() {
        double[] arrayToReport = magicNumbersCreator.generateRandomMagicDoubles().stream()
                .mapToDouble(Double::doubleValue)
                .toArray();
        return percentiles.stream()
                .map(percentile -> StatUtils.percentile(arrayToReport, percentile))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/stat", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Double> get() {
        return generateReport();
    }
}

