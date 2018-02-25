package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserPageTest extends InstagramPageTest {

    @Test
    public void load_natgeo() {
        UserPage userPage = new UserPage("natgeo");
        userPage.load();

        User user = userPage.getUser();
        System.out.println("User:\n" + user.toDetailedString());

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
    public void load_javagram() {
        UserPage userPage = new UserPage("javagram_tester");
        userPage.load();

        User user = userPage.getUser();
        System.out.println("User:\n" + user.toDetailedString());

        assertEquals("javagram_tester", user.getId());
        assertEquals("Javagram", user.getName());
        assertEquals(null, user.getDescription());
        assertEquals(null, user.getWebsiteUrl());
        assertFalse(user.hasWebsite());
        assertFalse(user.isVerified());
        assertEquals(0, user.getPostsCount());
        assertTrue(user.getFollowersCount() >= 0);
        assertEquals(1, user.getFollowingCount());
        assertEquals(0, userPage.getRecentPosts().size());
    }

    @Test
    public void parseCount_validString_correctCount() {
        assertEquals(123, UserPage.parseCount("123"));
        assertEquals(1230, UserPage.parseCount("1,230"));
        assertEquals(12300, UserPage.parseCount("12.3k"));
        assertEquals(123000, UserPage.parseCount("123k"));
        assertEquals(12300000, UserPage.parseCount("12.3m"));
        assertEquals(123000000, UserPage.parseCount("123m"));
    }

    @Test
    public void parseCount_invalidString_unknownValue() {
        assertEquals(Post.COUNT_UNKNOWN, UserPage.parseCount(null));
        assertEquals(Post.COUNT_UNKNOWN, UserPage.parseCount(""));
    }

}