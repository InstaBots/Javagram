package net.steppschuh.instabots.bots;

import net.steppschuh.instabots.actions.post.LikePostAction;
import net.steppschuh.instabots.filter.CompositeFilter;
import net.steppschuh.instabots.filter.post.LikesCountFilter;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.Tags;
import net.steppschuh.instabots.pages.ExplorePage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostLikingBot extends Bot {

    public PostLikingBot() {
        super();
    }

    @Override
    protected void onStart() {
        Tags tags = new Tags(Arrays.asList(
                "photography",
                "travelphotography",
                "landscapephotography",
                "naturephotography",
                "wildlifephotography"
        ));

        try {
            while (true) {
                for (String tag : tags) {
                    likeRecentPosts(tag);
                    Thread.sleep((int) (Math.random() * TimeUnit.SECONDS.toMillis(30)));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {

    }

    private void likeRecentPosts(String tag) {
        // get some recent posts from the explore page
        ExplorePage explorePage = new ExplorePage(tag);
        explorePage.load();
        List<Post> recentPosts = explorePage.getRecentPosts();

        // filter the posts
        CompositeFilter<Post> filter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MINIMUM, 10),
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 100)
        ));
        List<Post> filteredPosts = filter.applyTo(recentPosts);
        LOGGER.fine(filteredPosts.size() + " posts matched the filter");

        // like the posts that matched the filter
        List<LikePostAction> likePostActions = new ArrayList<>();
        for (Post post : filteredPosts) {
            LikePostAction likePostAction = new LikePostAction(post.getId());
            likePostActions.add(likePostAction);
        }

        for (LikePostAction likePostAction : likePostActions) {
            //likePostAction.perform();
            LOGGER.info("Simulated like for post: " + likePostAction.getPostId());
        }
    }

}
