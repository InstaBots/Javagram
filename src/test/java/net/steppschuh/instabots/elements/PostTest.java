package net.steppschuh.instabots.elements;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Steppschuh on 07.02.18.
 */
public class PostTest {

    @Test
    public void getPostIdFromUrl_validUrl_correctId() throws Exception {
        String url = "https://www.instagram.com/p/BekncLgn3AQ/?tagged=natgeo";
        String expectedId = "BekncLgn3AQ";
        String actualId = Post.getPostIdFromUrl(url);
        assertEquals(expectedId, actualId);
    }

    @Test
    public void getPostIdFromUrl_invalidUrl_throwsException() throws Exception {
        String url = "https://www.instagram.com/explore/tags/natgeo/";
        try {
            Post.getPostIdFromUrl(url);
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
                "\uD83D\uDCF8#dogu_people\n" +
                "\uD83D\uDCF8#humanity_shot_\n" +
                "#photography \n" +
                "#fotorgaffdiyarim";

        List<String> expectedTags = Arrays.asList(
                "dogu_people",
                "humanity_shot_",
                "photography",
                "fotorgaffdiyarim"
        );
        List<String> actualTags = Post.getTagsFromTitle(title);

        actualTags.forEach(System.out::println);

        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void getTagsFromTitle_invalidTitle_noTags() throws Exception {
        String title = "";
        List<String> expectedTags = new ArrayList<>();
        List<String> actualTags = Post.getTagsFromTitle(title);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

}