package net.steppschuh.instabots.elements;

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

    //private static final Pattern TAG_PATTERN = Pattern.compile("(?:^|\\s|[\\p{Punct}&&[^/]])(#[\\p{L}0-9-_]+)");
    private static final Pattern TAG_PATTERN = Pattern.compile("[##]+([A-Za-z0-9-_]+)");

    protected String id;
    protected String imageUrl;
    protected String title;
    protected int likesCount;
    protected int commentsCount;

    public Post(@Nonnull WebElement rootElement) {
        super(rootElement);
    }

    public static String getPostIdFromUrl(String url) {
        String postId = ParserUtil.getStringBetween(url, "/p/", "/");
        if (!isValidPostId(postId)) {
            throw new IllegalArgumentException("Unable to extract post ID from URL: " + url);
        }
        return postId;
    }

    public static boolean isValidPostId(String postId) {
        if (postId == null || postId.length() != 11) {
            return false;
        } else if (postId.contains("/")) {
            return false;
        }
        // TODO: add more checks
        return true;
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
}
