package net.steppschuh.instabots.filter.user;

import net.steppschuh.instabots.filter.AbstractFilter;
import net.steppschuh.instabots.models.User;

public class FollowingCountFilter extends AbstractFilter<User> {

    public enum Mode {
        MINIMUM, MAXIMUM
    }

    private Mode mode;
    private int count;

    public FollowingCountFilter(Mode mode, int count) {
        this.mode = mode;
        this.count = count;
    }

    @Override
    public boolean matches(User post) {
        if (post.getFollowingCount() == User.COUNT_UNKNOWN) {
            return false;
        }
        switch (mode) {
            case MINIMUM:
                return post.getFollowingCount() >= count;
            case MAXIMUM:
                return post.getFollowingCount() <= count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
