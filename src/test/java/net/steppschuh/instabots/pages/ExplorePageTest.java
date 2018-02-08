package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.elements.ExplorePostPreviewElement;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExplorePageTest extends InstagramPageTest {

    @Test
    public void load() throws Exception {
        ExplorePage explorePage = new ExplorePage(chromeDriver, "natgeo");
        explorePage.load();

        List<ExplorePostPreviewElement> topPosts = explorePage.getTopPosts();
        assertEquals(9, topPosts.size());

        for (ExplorePostPreviewElement topPost : topPosts) {
            System.out.println(topPost.toString());
        }

        List<ExplorePostPreviewElement> recentPosts = explorePage.getRecentPosts();
        assertTrue(recentPosts.size() >= 9);
    }

}