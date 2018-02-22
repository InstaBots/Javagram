package net.steppschuh.instabots.bots;

import net.steppschuh.instabots.actions.post.LikePostAction;
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
        //ExplorePage explorePage = new ExplorePage(chromeDriver, "natgeo");
        //explorePage.load();

        UserPage userPage = new UserPage("steppschuh");
        userPage.load();

        List<Post> recentPosts = userPage.getRecentPosts();
        List<LikePostAction> likePostActions = new ArrayList<>();

        for (Post recentPost : recentPosts) {
            // TODO. filter posts based on stuff
            LikePostAction likePostAction = new LikePostAction(recentPost.getId());
            likePostActions.add(likePostAction);
            if (likePostActions.size() >= 3) {
                break;
            }
        }

        for (LikePostAction likePostAction : likePostActions) {
            //Javagram.getDatabase().persistQueuedAction(likePostAction);

        }
    }

    @Override
    protected void onStop() {

    }

}
