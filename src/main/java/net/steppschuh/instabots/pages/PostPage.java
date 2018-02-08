package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.utils.ParserUtil;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;

/**
 * Created by Steppschuh on 08.02.18.
 */
public class PostPage extends InstagramPage {

    private static final String POST_BY_ID_URL = InstagramPage.HOME_URL + "p/";

    public PostPage(@Nonnull ChromeDriver chromeDriver) {
        super(chromeDriver);
    }

    @Override
    public void load() {

    }

    public static String getPostUrl(String postId) {
        return POST_BY_ID_URL + postId + "/";
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

}
