package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.pages.InstagramPage;
import net.steppschuh.instabots.utils.ParserUtil;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Steppschuh on 07.02.18.
 */
public class Post extends InstagramElement {

    private static final Pattern TAG_PATTERN = Pattern.compile("[##]+([A-Za-z0-9-_]+)");

    private String id;
    private String imageUrl;
    private String title;
    private int likesCount;
    private int commentsCount;

    public Post(@Nonnull WebElement rootElement) {
        super(rootElement);
    }

    public static List<String> getTagsFromTitle(String title) {
        List<String> tags = new ArrayList<>();
        Matcher matcher = TAG_PATTERN.matcher(title);
        while (matcher.find()) {
            String tag = matcher.group();
            tag = tag.substring(1).trim();
            tags.add(tag);
        }
        return tags;
    }

    public static String getTruncatedTitle(String title) {
        return getTruncatedTitle(title, 10);
    }

    public static String getTruncatedTitle(String title, int maximumLength) {
        if (title.length() <= maximumLength) {
            return title;
        }
        return title.substring(0, maximumLength) + "[...]";
    }

    /*
        Getter & Setter
     */

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

}
