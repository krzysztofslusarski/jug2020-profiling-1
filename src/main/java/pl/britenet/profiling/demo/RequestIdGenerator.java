package pl.britenet.profiling.demo;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class RequestIdGenerator {
    private static Random random = ThreadLocalRandom.current();

    private static MagicNumbersCreator magicNumbersCreator = new MagicNumbersCreatorImpl();

    static String generate() {
        double sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += doSomeMagic(random.nextDouble());
        }
        return "ID-" + sum;
    }

    private static double doSomeMagic(double arg) {
        List<Integer> magicNumbers = magicNumbersCreator.generateRandomMagicIntegers();

        double sum = 0;
        for (int i = 0; i < magicNumbers.size(); i++) {
            sum += (arg * magicNumbers.get(i));
        }
        return sum;
    }
}
