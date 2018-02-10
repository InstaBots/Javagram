package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserPageTest extends InstagramPageTest {

    @Test
    public void load() {
        UserPage userPage = new UserPage(chromeDriver, "natgeo");
        userPage.load();

        System.out.println("User:\n");
        User user = userPage.getUser();
        System.out.println(user.toDetailedString());

        assertEquals("natgeo", user.getId());
        assertEquals("National Geographic", user.getName());
        assertEquals("Experience the world through the eyes of National Geographic photographers.", user.getDescription());
        assertEquals("nationalgeographic.com/photography", user.getWebsiteUrl());
        assertTrue(user.isVerified());
        assertTrue(user.getPostsCount() > 16000);
        assertTrue(user.getFollowersCount() > 8500000);
        assertTrue(user.getFollowingCount() > 100);

        System.out.println("\nRecent posts:\n");
        List<Post> recentPosts = userPage.getRecentPosts();
        for (Post post : recentPosts) {
            System.out.println(post.toDetailedString() + "\n");
        }
        assertTrue(recentPosts.size() >= 9);
    }

    @Test
    public void parseCount_validString_correctCount() {
        assertEquals(Post.COUNT_UNKNOWN, UserPage.parseCount(null));
        assertEquals(Post.COUNT_UNKNOWN, UserPage.parseCount(""));
        assertEquals(123, UserPage.parseCount("123"));
        assertEquals(1234, UserPage.parseCount("1,234"));
        assertEquals(12300, UserPage.parseCount("12.3k"));
        assertEquals(123000, UserPage.parseCount("123k"));
        assertEquals(12300000, UserPage.parseCount("12.3m"));
        assertEquals(123000000, UserPage.parseCount("123m"));
    }
}