package pl.britenet.profiling.demo.jit;

import java.util.Random;

/**
 * http://blog.ragozin.info/2019/03/lies-darn-lies-and-sampling-bias.html
 */
class RandomStringUtils {
    String generate() {
        int leftLimit = 'a';
        int rightLimit = 'z';
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }
}
