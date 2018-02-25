package net.steppschuh.instabots.bots;

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
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class PostLikingBot extends Bot {

    public PostLikingBot() {
        super();
    }

    @Override
    protected void onStart() {
        Tags tags = new Tags(Arrays.asList(
                "portraitphotography",
                "top_portraits",
                "portrait_ig",
                "discoverportrait",
                "earth_portraits",
                "portraitpage",
                "pursuitofportraits",
                "portraiture",
                "portraitshoot",
                "makeportraitsmag",
                "agameofportraits",
                "postthepeople"
        ));

        while (true) {
            for (String tag : tags) {
                LOGGER.info("Liking posts for tag: " + tag);
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

        // reverse to process oldest posts first
        Collections.reverse(likePostActions);

        // filter the posts
        CompositeFilter<Post> filter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MINIMUM, 3), // should have more than 3 likes
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 50), // should have less than 50 likes
                new RecencyFilter(RecencyFilter.Mode.BEFORE, 1, TimeUnit.MINUTES), // should be at least 1 minute old
                new RecencyFilter(RecencyFilter.Mode.AFTER, 1, TimeUnit.DAYS) // should not be older than 1 day
        ));

        for (LikePostAction likePostAction : likePostActions) {
            try {
                PostPage postPage = new PostPage(likePostAction.getPostId());
                postPage.load();
                Post post = postPage.getPost();

                SleepUtil.sleep(2, 5, TimeUnit.SECONDS);

                if (!filter.matches(post)) {
                    LOGGER.finest("Filter didn't match post: " + post.toDetailedString());
                    continue;
                }

                postPage.like();
                //LOGGER.info("Simulated like for post: " + post.toDetailedString());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Unable to process post: " + likePostAction.getPostId());
            }
        }
    }

}
