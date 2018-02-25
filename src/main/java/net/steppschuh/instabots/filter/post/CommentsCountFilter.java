package net.steppschuh.instabots.filter.post;

import net.steppschuh.instabots.filter.AbstractFilter;
import net.steppschuh.instabots.models.Post;

public class CommentsCountFilter extends AbstractFilter<Post> {

    public enum Mode {
        MINIMUM, MAXIMUM
    }

    private Mode mode;
    private int count;

    public CommentsCountFilter(Mode mode, int count) {
        this.mode = mode;
        this.count = count;
    }

    @Override
    public boolean matches(Post post) {
        if (post.getCommentsCount() == Post.COUNT_UNKNOWN) {
            return false;
        }
        switch (mode) {
            case MINIMUM:
                return post.getCommentsCount() >= count;
            case MAXIMUM:
                return post.getCommentsCount() <= count;
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
