package net.steppschuh.instabots.utils;

import java.util.concurrent.TimeUnit;

public final class SleepUtil {

    public static void sleep(long minimumDuration, long maximumDuration, TimeUnit timeUnit) {
        long delta = timeUnit.toMillis(maximumDuration) - timeUnit.toMillis(minimumDuration);
        long duration = timeUnit.toMillis(minimumDuration) + (long) (Math.random() * delta);
        sleep(duration, TimeUnit.MILLISECONDS);
    }

    public static void sleep(long duration, TimeUnit timeUnit) {
        long milliseconds = timeUnit.toMillis(duration);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
