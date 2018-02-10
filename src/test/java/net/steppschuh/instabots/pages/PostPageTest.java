package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostPageTest extends InstagramPageTest {

    @Test
    public void load_existingPost_parsesPost() {
        PostPage postPage = new PostPage(chromeDriver, "Bd5ec0Hjds7");
        postPage.load();

        Post post = postPage.getPost();
        System.out.println("Post:\n" + post.toDetailedString());

        assertEquals("Bd5ec0Hjds7", post.getId());
        assertEquals("By @drewtrush // A red fox tries to stay warm as the temperature dips well below freezing. The red fox is the most widely distributed member of the fox family. #follow along with me @drewtrush to learn more about wildlife.\n" +
                "______________________________________________\n" +
                "\n" +
                "#commonground", post.getTitle());

        assertTrue(post.getLikesCount() > 1174000);
        assertTrue(post.getImageUrl().contains(".jpg"));
        assertEquals(2, post.getTags().size());
        // date 13/1/18

        User user = post.getUser();
        assertEquals("natgeo", user.getId());
    }

    @Test
    public void load_nonExistingPost_throwsException() {
        PostPage postPage = new PostPage(chromeDriver, "foobar");
        try {
            postPage.load();
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}