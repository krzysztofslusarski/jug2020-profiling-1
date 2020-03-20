package pl.britenet.profiling.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicNumbersCreatorImpl implements MagicNumbersCreator {
    private static List<Integer> MAGIC_INTS = IntStream.range(0, 1_000).boxed().collect(Collectors.toList());
    private static int DOUBLE_LIST_SIZE = 10_000_000;
    private static List<Double> reportList;

    static {
        Random random = new Random();
        reportList = new ArrayList<>(DOUBLE_LIST_SIZE);
        for (int i = 0; i < DOUBLE_LIST_SIZE; i++) {
            reportList.add(random.nextDouble());
        }
    }

    public static void beforeJIT() {
        MAGIC_INTS = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        DOUBLE_LIST_SIZE = 10_000;
        Random random = new Random();
        reportList = new ArrayList<>(DOUBLE_LIST_SIZE);
        for (int i = 0; i < DOUBLE_LIST_SIZE; i++) {
            reportList.add(random.nextDouble());
        }
    }

    public static void afterJIT() {
        MAGIC_INTS = IntStream.range(0, 1_000).boxed().collect(Collectors.toList());
        DOUBLE_LIST_SIZE = 10_000_000;
        Random random = new Random();
        reportList = new ArrayList<>(DOUBLE_LIST_SIZE);
        for (int i = 0; i < DOUBLE_LIST_SIZE; i++) {
            reportList.add(random.nextDouble());
        }
    }

    @Override
    public List<Double> generateRandomMagicDoubles() {
        return reportList;
    }

    @Override
    public List<Integer> generateRandomMagicIntegers() {
        return new LinkedList<>(MAGIC_INTS);
    }
}
