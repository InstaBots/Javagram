package net.steppschuh.instabots.bots;

import net.steppschuh.instabots.actions.post.LikePostAction;
import net.steppschuh.instabots.filter.CompositeFilter;
import net.steppschuh.instabots.filter.post.LikesCountFilter;
import net.steppschuh.instabots.filter.post.RecencyFilter;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.Tags;
import net.steppschuh.instabots.models.User;
import net.steppschuh.instabots.pages.ExplorePage;
import net.steppschuh.instabots.pages.PostPage;
import net.steppschuh.instabots.pages.UserPage;
import net.steppschuh.instabots.utils.SleepUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class UserPostsLikingBot extends Bot {

    private User user;
    private UserPage userPage;

    public UserPostsLikingBot() {
        super();
    }

    public UserPostsLikingBot(User user) {
        this.user = user;
    }

    @Override
    protected void onStart() {
        userPage = new UserPage(user);
        userPage.load();

        if (user.getFollowersCount() < 25 || user.getFollowersCount() > 2000) {
            LOGGER.fine("Not liking any posts from " + user.toCompactString() +
                    ", followers count not in range: " + user.getFollowersCount());
            return;
        }

        if (user.getFollowingCount() < 25 || user.getFollowingCount() > 1000) {
            LOGGER.fine("Not liking any posts from " + user.toCompactString() +
                    ", following count not in range: " + user.getFollowingCount());
            return;
        }

        if (user.getPostsCount() < 5 || user.getPostsCount() > 500) {
            LOGGER.fine("Not liking any posts from " + user.toCompactString() +
                    ", posts count not in range: " + user.getPostsCount());
            return;
        }

        likeRecentPosts(3);
    }

    @Override
    protected void onStop() {

    }

    private void likeRecentPosts(int maximumLikedPostsCount) {
        LOGGER.fine("Liking up to " + maximumLikedPostsCount + " posts from user: " + user.toDetailedString());

        List<Post> recentPosts = userPage.getRecentPosts();

        // filter the posts previews
        CompositeFilter<Post> previewFilter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MINIMUM, 10),
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 500)
        ));
        List<Post> filteredPosts = previewFilter.applyTo(recentPosts);
        LOGGER.fine(filteredPosts.size() + " posts previews matched the filter");

        // like the posts that matched the filter
        List<LikePostAction> likePostActions = new ArrayList<>();
        for (Post post : filteredPosts) {
            LikePostAction likePostAction = new LikePostAction(post.getId());
            likePostActions.add(likePostAction);
        }

        Collections.shuffle(likePostActions);

        // filter the posts
        CompositeFilter<Post> filter = new CompositeFilter<>(CompositeFilter.Mode.ALL, Arrays.asList(
                new LikesCountFilter(LikesCountFilter.Mode.MINIMUM, 10), // should have more than 3 likes
                new LikesCountFilter(LikesCountFilter.Mode.MAXIMUM, 500), // should have less than 50 likes
                new RecencyFilter(RecencyFilter.Mode.BEFORE, 1, TimeUnit.DAYS), // should be at least 1 minute old
                new RecencyFilter(RecencyFilter.Mode.AFTER, 60, TimeUnit.DAYS) // should not be older than 1 day
        ));

        int likedPostsCount = 0;
        for (LikePostAction likePostAction : likePostActions) {
            try {
                PostPage postPage = new PostPage(likePostAction.getPostId());
                postPage.load();
                Post post = postPage.getPost();

                SleepUtil.sleep(1, 3, TimeUnit.SECONDS);

                if (!filter.matches(post)) {
                    LOGGER.finest("Filter didn't match post: " + post.toDetailedString());
                    continue;
                }

                postPage.like();
                likedPostsCount++;
                if (likedPostsCount == maximumLikedPostsCount) {
                    break;
                }

                SleepUtil.sleep(1, 3, TimeUnit.SECONDS);

                //LOGGER.info("Simulated like for post: " + post.toDetailedString());
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Unable to process post: " + likePostAction.getPostId());
            }
        }

        LOGGER.fine("Liked " + likedPostsCount + " posts from user: " + user.toCompactString());
    }

}
