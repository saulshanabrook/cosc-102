package Utils;

/**
 * Inspired by Guava's preconditions.
 * https://code.google.com/p/guava-libraries/wiki/PreconditionsExplained
 * Reimplemented due to library use constraints.
 */
public class Preconditions {
    public static <T> T checkNotNull(T arg) {
        if (arg == null) {
            throw new NullPointerException();
        }
        return arg;
    }
}
