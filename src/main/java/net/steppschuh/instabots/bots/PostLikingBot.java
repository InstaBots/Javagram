package net.steppschuh.instabots.bots;

import javafx.geometry.Pos;
import net.steppschuh.instabots.actions.post.LikePostAction;
import net.steppschuh.instabots.filter.CompositeFilter;
import net.steppschuh.instabots.filter.post.LikesCountFilter;
import net.steppschuh.instabots.filter.post.RecencyFilter;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.Tags;
import net.steppschuh.instabots.pages.ExplorePage;
import net.steppschuh.instabots.pages.PostPage;
import net.steppschuh.instabots.utils.SleepUtil;

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

        while (true) {
            for (String tag : tags) {
                likeRecentPosts(tag);
                SleepUtil.sleep(5, 10, TimeUnit.SECONDS);
            }
            SleepUtil.sleep(30, 60, TimeUnit.SECONDS);
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

        // filter the posts previews
        CompositeFilter<Post> previewFilter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 100)
        ));
        List<Post> filteredPosts = previewFilter.applyTo(recentPosts);
        LOGGER.fine(filteredPosts.size() + " posts previews matched the filter");

        // like the posts that matched the filter
        List<LikePostAction> likePostActions = new ArrayList<>();
        for (Post post : filteredPosts) {
            LikePostAction likePostAction = new LikePostAction(post.getId());
            likePostActions.add(likePostAction);
        }

        // filter the posts
        CompositeFilter<Post> filter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MINIMUM, 5), // should have more than 5 likes
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 100), // should have less than 100 likes
                new RecencyFilter(RecencyFilter.Mode.BEFORE, 1, TimeUnit.MINUTES), // should be at least 1 minute old
                new RecencyFilter(RecencyFilter.Mode.AFTER, 1, TimeUnit.DAYS) // should not be older than 1 day
        ));

        for (LikePostAction likePostAction : likePostActions) {
            PostPage postPage = new PostPage(likePostAction.getPostId());
            postPage.load();
            Post post = postPage.getPost();

            SleepUtil.sleep(2, 5, TimeUnit.SECONDS);

            if (!filter.matches(post)) {
                LOGGER.finest("Filter didn't match post: " + post.toDetailedString());
                continue;
            }

            //postPage.like();
            LOGGER.info("Simulated like for post: " + post.toDetailedString());
        }
    }

}
