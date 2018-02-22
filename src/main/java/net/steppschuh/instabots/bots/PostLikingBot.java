package net.steppschuh.instabots.bots;

import net.steppschuh.instabots.Javagram;
import net.steppschuh.instabots.actions.post.LikePostAction;
import net.steppschuh.instabots.actions.post.UnlikePostAction;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.pages.UserPage;

import java.util.ArrayList;
import java.util.List;

public class PostLikingBot extends Bot {

    public PostLikingBot() {
        super();
    }

    @Override
    protected void onStart() {
        UserPage userPage = new UserPage("steppschuh");
        userPage.load();

        List<Post> recentPosts = userPage.getRecentPosts();
        List<LikePostAction> likePostActions = new ArrayList<>();
        List<UnlikePostAction> unlikePostActions = new ArrayList<>();

        for (Post recentPost : recentPosts) {
            // TODO: filter posts based on stuff
            LikePostAction likePostAction = new LikePostAction(recentPost.getId());
            likePostActions.add(likePostAction);

            UnlikePostAction unlikePostAction = new UnlikePostAction(recentPost.getId());
            unlikePostActions.add(unlikePostAction);

            if (likePostActions.size() >= 3) {
                break;
            }
        }

        for (LikePostAction likePostAction : likePostActions) {
            likePostAction.perform();
        }

        for (UnlikePostAction unlikePostAction : unlikePostActions) {
            //Javagram.getDatabase().persistQueuedAction(unlikePostAction);
            unlikePostAction.perform();
        }
    }

    @Override
    protected void onStop() {

    }

}
