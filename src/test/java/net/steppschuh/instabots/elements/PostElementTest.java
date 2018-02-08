package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.models.Tags;
import net.steppschuh.instabots.pages.PostPage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Steppschuh on 07.02.18.
 */
public class PostElementTest {

    @Test
    public void getPostIdFromUrl_validUrl_correctId() throws Exception {
        String url = "https://www.instagram.com/p/BekncLgn3AQ/?tagged=natgeo";
        String expectedId = "BekncLgn3AQ";
        String actualId = PostPage.getPostIdFromUrl(url);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void getPostIdFromUrl_invalidUrl_throwsException() throws Exception {
        String url = "https://www.instagram.com/explore/tags/natgeo/";
        try {
            PostPage.getPostIdFromUrl(url);
            fail("Expected exception not thrown");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void getTagsFromTitle_validTitle_correctTags() throws Exception {
        String title = "Güzel ilçemin  güzel insanı ⏩Ummahan KONAKÇI⏪\n" +
                "Rabbim sağlıklı hayırlı uzun ömürler nasip etsin inşallah....\n" +
                "Eskişehir Han İlçesi\n" +
                "@zubydesalar\n" +
                "···\n" +
                "“ ...\n" +
                "...\n" +
                "\uD83D\uDCF8#dogu_people " +
                "\uD83D\uDCF8#humanity_shot_\n" +
                "#photography \n" +
                "#fotorgaffdiyarim";

        List<String> expectedTags = Arrays.asList(
                "dogu_people",
                "humanity_shot_",
                "photography",
                "fotorgaffdiyarim"
        );
        List<String> actualTags = Tags.getTagsFromTitle(title);

        actualTags.forEach(System.out::println);

        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void getTagsFromTitle_invalidTitle_noTags() throws Exception {
        String title = "";
        List<String> expectedTags = new ArrayList<>();
        List<String> actualTags = Tags.getTagsFromTitle(title);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

}