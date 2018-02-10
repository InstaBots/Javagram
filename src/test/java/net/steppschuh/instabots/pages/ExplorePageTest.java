package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExplorePageTest extends InstagramPageTest {

    @Test
    public void load_validTag_parsesPosts() throws Exception {
        ExplorePage explorePage = new ExplorePage(chromeDriver, "natgeo");
        explorePage.load();

        assertEquals("natgeo", explorePage.getTag());
        assertTrue(explorePage.getTotalPostsCount() > 10289000);

        List<Post> topPosts = explorePage.getTopPosts();
        assertEquals(9, topPosts.size());

        for (Post topPost : topPosts) {
            System.out.println(topPost.toDetailedString() + "\n");
        }

        List<Post> recentPosts = explorePage.getRecentPosts();
        assertTrue(recentPosts.size() >= 9);
    }

    @Test
    public void load_unknownTag_noPosts() throws Exception {
        ExplorePage explorePage = new ExplorePage(chromeDriver, "asdfkljhasdflkjhasdf");
        explorePage.load();

        assertEquals(0, explorePage.getTotalPostsCount());
        assertEquals(0, explorePage.getTopPosts().size());
        assertEquals(0, explorePage.getRecentPosts().size());
    }

}