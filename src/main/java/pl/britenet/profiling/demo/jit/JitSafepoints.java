package pl.britenet.profiling.demo.jit;

import java.security.MessageDigest;
import lombok.SneakyThrows;

/**
 * http://blog.ragozin.info/2019/03/lies-darn-lies-and-sampling-bias.html
 */
class JitSafepoints {
    public static void main(String[] args) {
        JitSafepoints test = new JitSafepoints();
        while (true) {
            test.execute();
        }
    }

    private void execute() {
        long N = 5 * 1000 * 1000;
        RandomStringUtils randomStringUtils = new RandomStringUtils();
        for (long i = 0; i < N; i++) {
            String text = randomStringUtils.generate();
            crypt(text);
        }
    }

    @SneakyThrows
    private String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder hexString = new StringBuilder();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes());
        byte[] hash = messageDigest.digest();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }
}
