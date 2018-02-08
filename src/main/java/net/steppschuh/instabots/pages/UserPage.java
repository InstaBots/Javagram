package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.elements.UserPostPreviewElement;
import net.steppschuh.instabots.models.User;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends InstagramPage {

    private User user = new User();

    private List<UserPostPreviewElement> recentPosts = new ArrayList<>();

    public UserPage(@Nonnull ChromeDriver chromeDriver, String userId) {
        super(chromeDriver);
        user.setId(userId);
    }

    @Override
    public void load() {

    }

    /*
        Getter & Setter
     */

    public User getUser() {
        return user;
    }

    public List<UserPostPreviewElement> getRecentPosts() {
        return recentPosts;
    }

}
