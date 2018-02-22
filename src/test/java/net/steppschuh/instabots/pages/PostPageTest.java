package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class PostPageTest extends InstagramPageTest {

    @Test
    public void load_photoPost_parsesPost() {
        PostPage postPage = new PostPage("Bd5ec0Hjds7");
        postPage.load();

        Post post = postPage.getPost();
        System.out.println("Post:\n" + post.toDetailedString());

        assertEquals(Post.Type.PHOTO, post.getType());
        assertEquals("Bd5ec0Hjds7", post.getId());
        assertEquals("By @drewtrush // A red fox tries to stay warm as the temperature dips " +
                "well below freezing. The red fox is the most widely distributed member of the fox family. " +
                "#follow along with me @drewtrush to learn more about wildlife.\n" +
                "______________________________________________\n\n#commonground", post.getTitle());

        assertTrue(post.getLikesCount() > 1174000);
        assertTrue(post.getImageUrl().contains(".jpg"));
        assertEquals(2, post.getTags().size());
        assertEquals(1515864568000L, post.getTimestamp()); // 13/1/18

        User user = post.getUser();
        assertEquals("natgeo", user.getId());
    }

    @Test
    public void load_galleryPost_parsesPost() {
        PostPage postPage = new PostPage("Be1QneAjanD");
        postPage.load();

        Post post = postPage.getPost();
        System.out.println("Post:\n" + post.toDetailedString());

        assertEquals(Post.Type.GALLERY, post.getType());
        assertEquals("Be1QneAjanD", post.getId());
        assertEquals("Photograph by David Chancellor @chancellordavid - leopard, South Africa " +
                "- the extraordinary agility of a male leopard trying to drag a rock python out of a thorn bush " +
                "- scroll through for the full story - the python wasn’t coming down; the leopard " +
                "wasn’t going up, so they sparred like this whilst I watched and fell even more in love with " +
                "leopards, the most amazing of all the big bad cats. To see more of my work and projects " +
                "follow me here @natgeo @chancellordavid @thephotosociety and @everydayextinction #leopard #southafrica " +
                "#conservation #conserving #bigcats #nopoaching #notrade", post.getTitle());

        assertTrue(post.getLikesCount() > 948000);
        assertTrue(post.getImageUrl().contains(".jpg"));
        assertEquals(7, post.getTags().size());
        assertEquals(1517870581000L, post.getTimestamp());

        User user = post.getUser();
        assertEquals("natgeo", user.getId());
    }

    @Test
    public void load_videoPost_parsesPost() {
        PostPage postPage = new PostPage("Be_r-ZYDDv7");
        postPage.load();

        Post post = postPage.getPost();
        System.out.println("Post:\n" + post.toDetailedString());

        assertEquals(Post.Type.VIDEO, post.getType());
        assertEquals("Be_r-ZYDDv7", post.getId());
        assertEquals("Video by @renan_ozturk @camp4collective\n" +
                "Moonlight moving across a remote canyon in Bears Ears National Monument. I never used to " +
                "call this place I cherished “Bears Ears.” This has been a recent phenomena since the designation, " +
                "and subsequent 85% reduction, of the most beautiful National Monument I’ve ever called home. " +
                "But the change in name is meaningful, inclusive of its American Indian land-base and its federal " +
                "recognition and all around its bringing people together in discussion about its use. Bears Ears " +
                "is vastness I could never have known prior to flying transects this week with @shotsfromabove " +
                "@joeraven74 @taylorfreesolo and @filmguppy. We spent a good portion of our time continuing to " +
                "macgyver a new stabilized method to shoot RED 8K footage with the shotover G1. But most of our " +
                "time was spent taking in and trying to make sense of the continuity of what was over a million " +
                "acres of protected monument and is now a much more “at risk” contiguous and intact ecosystem. " +
                "The issues are complex. What’s not complex is the beauty and uniqueness of this landscape. " +
                "Thanks all who have helped to support our just budding film project to share inclusive and diverse stories. " +
                "~ see @renan_ozturk for more current images of #bearsearsnationalmonument", post.getTitle());

        assertTrue(post.getLikesCount() > 580000);
        assertTrue(post.getImageUrl().contains(".jpg"));
        assertEquals(1, post.getTags().size());
        assertEquals(1518220513000L, post.getTimestamp());

        User user = post.getUser();
        assertEquals("natgeo", user.getId());
    }

    @Test
    public void load_nonExistingPost_throwsException() {
        PostPage postPage = new PostPage("foobar");
        try {
            postPage.load();
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

}