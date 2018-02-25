package net.steppschuh.instabots.filter.post;

import net.steppschuh.instabots.filter.AbstractFilter;
import net.steppschuh.instabots.models.Post;

import java.util.concurrent.TimeUnit;

public class RecencyFilter extends AbstractFilter<Post> {

    public enum Mode {
        BEFORE, AFTER
    }

    private Mode mode;
    private long timestamp;

    public RecencyFilter(Mode mode, long timestamp) {
        this.mode = mode;
        this.timestamp = timestamp;
    }

    public RecencyFilter(Mode mode, long duration, TimeUnit timeUnit) {
        this.mode = mode;
        this.timestamp = System.currentTimeMillis() - timeUnit.toMillis(duration);
    }

    @Override
    public boolean matches(Post post) {
        if (post.getTimestamp() <= 0) {
            return false;
        }
        switch (mode) {
            case BEFORE:
                return post.getTimestamp() < timestamp;
            case AFTER:
                return post.getTimestamp() > timestamp;
        }
        throw new RuntimeException("Unable to evaluate matches using mode: " + mode);
    }

    /*
        Getter & Setter
     */

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
