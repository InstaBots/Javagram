package net.steppschuh.instabots.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TagsTest {

    private static final String VALID_TITLE = "Güzel ilçemin  güzel insanı ⏩Ummahan KONAKÇI⏪\n" +
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

    @Test
    public void getTagsFromTitle_validTitle_correctTags() throws Exception {
        List<String> expectedTags = Arrays.asList(
                "dogu_people",
                "humanity_shot_",
                "photography",
                "fotorgaffdiyarim"
        );
        List<String> actualTags = Tags.getTagListFromString(VALID_TITLE);

        actualTags.forEach(System.out::println);

        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void getTagsFromTitle_invalidTitle_noTags() throws Exception {
        String title = "";
        List<String> expectedTags = new ArrayList<>();
        List<String> actualTags = Tags.getTagListFromString(title);
        assertArrayEquals(expectedTags.toArray(), actualTags.toArray());
    }

    @Test
    public void from_validTitle_correctTags() {
        Tags tags = Tags.from(VALID_TITLE);
        System.out.println(tags);
    }

}