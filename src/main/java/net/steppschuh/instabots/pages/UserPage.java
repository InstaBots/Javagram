package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.elements.UserPostPreview;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends InstagramPage {

    private String userId;
    private String userName;
    private int postsCount;
    private int followersCount;

    private List<UserPostPreview> recentPosts = new ArrayList<>();

    public UserPage(@Nonnull ChromeDriver chromeDriver) {
        super(chromeDriver);
    }

    @Override
    public void load() {

    }

    /*
        Getter & Setter
     */

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public List<UserPostPreview> getRecentPosts() {
        return recentPosts;
    }

}
