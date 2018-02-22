package net.steppschuh.instabots.actions.post;

import net.steppschuh.instabots.pages.PostPage;

public class UnlikePostAction extends PostAction {

    public UnlikePostAction(String postId) {
        super(postId);
    }

    @Override
    public void perform() {
        PostPage postPage = new PostPage(postId);
        postPage.load();
        postPage.unlike();
    }

}
