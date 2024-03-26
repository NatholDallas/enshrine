package nathol.app.enshrine.common;

import java.util.Random;

public final class RandomCode {

    private RandomCode() {
    }

    public static String code(int length) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int num = new Random().nextInt(10);
            builder.append(Math.abs(num));
        }
        return builder.toString();
    }

}
