package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.pages.elements.PostPreviewElement;
import net.steppschuh.instabots.pages.elements.UserPostPreviewElement;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import net.steppschuh.instabots.utils.ParserUtil;
import net.steppschuh.instabots.utils.WebElementUtil;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class UserPage extends InstagramPage {

    private User user = new User();

    private List<Post> recentPosts = new ArrayList<>();

    public UserPage(String userId) {
        super();
        user.setId(userId);
    }

    @Override
    public String getUrl() {
        return user.getUrl();
    }

    @Override
    public void load() {
        super.load();

        loadUser();
        loadRecentPosts();
    }

    private void loadUser() {
        WebElement headerElement = chromeDriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/article/header"));

        WebElement descriptionContainerElement = headerElement.findElement(By.xpath("./section/div[2]"));

        // name
        WebElement userNameElement = descriptionContainerElement.findElement(By.tagName("h1"));
        user.setName(userNameElement.getText());

        // description

        if (WebElementUtil.hasElement(descriptionContainerElement, By.xpath("./span/span"))) {
            WebElement descriptionElement = descriptionContainerElement.findElement(By.xpath("./span/span"));
            user.setDescription(descriptionElement.getText());
        }

        // website
        if (WebElementUtil.hasElement(descriptionContainerElement, By.tagName("a"))) {
            WebElement websiteElement = descriptionContainerElement.findElement(By.tagName("a"));
            user.setWebsiteUrl(websiteElement.getText());
        }

        // verified
        user.setVerified(WebElementUtil.hasElement(headerElement, By.className("coreSpriteVerifiedBadge")));

        // stats
        WebElement statsContainer = headerElement.findElement(By.tagName("ul"));

        // posts count
        WebElement postCountContainer = statsContainer.findElement(By.xpath("./li[1]/span/span"));
        user.setPostsCount(parseCount(postCountContainer.getText()));

        // followers & following count
        WebElement followersCountContainer;
        WebElement followingCountContainer;
        if (WebElementUtil.hasElement(statsContainer, By.tagName("a"))) {
            followersCountContainer = statsContainer.findElement(By.xpath("./li[2]/a/span"));
            followingCountContainer = statsContainer.findElement(By.xpath("./li[3]/a/span"));
        } else {
            followersCountContainer = statsContainer.findElement(By.xpath("./li[2]/span/span"));
            followingCountContainer = statsContainer.findElement(By.xpath("./li[3]/span/span"));
        }
        user.setFollowersCount(parseCount(followersCountContainer.getText()));
        user.setFollowingCount(parseCount(followingCountContainer.getText()));
    }

    private void loadRecentPosts() {
        if (!chromeDriver.findElements(By.xpath("//*[@id=\"react-root\"]/section/main/article/div/h2")).isEmpty()) {
            LOGGER.finer("User has no posts yet");
            return;
        }

        WebElement recentPostsContainer = chromeDriver.findElement(
                By.xpath("//*[@id=\"react-root\"]/section/main/article/div[1]/div")
        );
        recentPosts = PostPreviewElement.getPosts(getUserPostPreviews(recentPostsContainer));
        for (Post recentPost : recentPosts) {
            recentPost.setUser(getUser());
        }
        LOGGER.finer("Found " + recentPosts.size() + " recent posts");
        UnorderedList<Post> recentPostsList = new UnorderedList<>(recentPosts);
        LOGGER.finest("\n" + recentPostsList.toString());
    }

    public void loadMoreRecentPosts() {
        // TODO: implement
    }

    /**
     * Parses strings like "123", "12.3k" or "5m" into int values.
     */
    public static int parseCount(String shortenedCount) {
        try {
            int factor = 1;
            if (shortenedCount.contains("k")) {
                factor *= 1000;
                shortenedCount = ParserUtil.getStringBefore(shortenedCount, "k");
            } else if (shortenedCount.contains("m")) {
                factor *= 1000000;
                shortenedCount = ParserUtil.getStringBefore(shortenedCount, "m");
            }
            if (shortenedCount.contains(".")) {
                shortenedCount = shortenedCount.replace(".", "");
                factor /= 10;
            } else if (shortenedCount.contains(",")) {
                shortenedCount = shortenedCount.replace(",", "");
            }
            return Integer.parseInt(shortenedCount) * factor;
        } catch (Exception e) {
            return Post.COUNT_UNKNOWN;
        }
    }

    private List<UserPostPreviewElement> getUserPostPreviews(WebElement container) {
        List<UserPostPreviewElement> postPreviews = new ArrayList<>();
        List<WebElement> rowContainers = container.findElements(By.xpath("./div"));
        for (WebElement rowContainer : rowContainers) {
            List<WebElement> postContainers = rowContainer.findElements(By.xpath("./div"));
            for (WebElement postContainer : postContainers) {
                postPreviews.add(new UserPostPreviewElement(this, postContainer));
            }
        }
        return postPreviews;
    }

    /*
        Getter & Setter
     */

    public User getUser() {
        return user;
    }

    public List<Post> getRecentPosts() {
        return recentPosts;
    }

}
