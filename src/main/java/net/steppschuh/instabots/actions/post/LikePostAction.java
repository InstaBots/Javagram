package net.steppschuh.instabots.actions.post;

import net.steppschuh.instabots.pages.PostPage;

public class LikePostAction extends PostAction {

    public LikePostAction(String postId) {
        super(postId);
    }

    @Override
    public void perform() {
        PostPage postPage = new PostPage(postId);
    }

}
