package net.steppschuh.instabots.actions.post;

import net.steppschuh.instabots.Javagram;
import net.steppschuh.instabots.actions.ActionTest;
import net.steppschuh.instabots.pages.PostPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LikePostActionTest extends ActionTest {

    private static String POST_ID = "BTd1vTaALy4";

    private void unlikePost() {
        UnlikePostAction unlikePostAction = new UnlikePostAction(POST_ID);
        unlikePostAction.perform();
    }

    @Test
    public void perform() {
        PostPage postPage = new PostPage(POST_ID);
        postPage.load();

        if (postPage.isLiked()) {
            postPage.unlike();
        }

        int previousLikesCount = postPage.getPost().getLikesCount();

        LikePostAction likePostAction = new LikePostAction(POST_ID);
        likePostAction.perform();

        Javagram.getChromeDriver().navigate().refresh();

        postPage.load();
        int newLikesCount = postPage.getPost().getLikesCount();

        unlikePost();

        assertEquals(previousLikesCount + 1, newLikesCount);
    }
}