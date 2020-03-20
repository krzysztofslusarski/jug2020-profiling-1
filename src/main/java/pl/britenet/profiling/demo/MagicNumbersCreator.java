package pl.britenet.profiling.demo;

import java.util.List;

public interface MagicNumbersCreator {
    List<Double> generateRandomMagicDoubles();

    List<Integer> generateRandomMagicIntegers();
}
