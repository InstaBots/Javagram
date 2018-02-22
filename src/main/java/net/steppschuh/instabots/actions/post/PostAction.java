package net.steppschuh.instabots.actions.post;

import net.steppschuh.instabots.actions.Action;

public abstract class PostAction extends Action {

    protected String postId;

    public PostAction(String postId) {
        this.postId = postId;
    }

    /*
        Getter & Setter
     */

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}
