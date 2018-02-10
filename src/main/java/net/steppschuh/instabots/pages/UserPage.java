package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.elements.PostPreviewElement;
import net.steppschuh.instabots.elements.UserPostPreviewElement;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import net.steppschuh.instabots.utils.ParserUtil;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserPage extends InstagramPage {

    private User user = new User();

    private List<Post> recentPosts = new ArrayList<>();

    public UserPage(@Nonnull ChromeDriver chromeDriver, String userId) {
        super(chromeDriver);
        user.setId(userId);
    }

    @Override
    public void load() {
        chromeDriver.get(user.getUrl());

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
        WebElement descriptionElement = descriptionContainerElement.findElement(By.xpath("./span/span"));
        user.setDescription(descriptionElement.getText());

        // website
        if (!descriptionContainerElement.findElements(By.tagName("a")).isEmpty()) {
            WebElement websiteElement = descriptionContainerElement.findElement(By.tagName("a"));
            user.setWebsiteUrl(websiteElement.getText());
        }

        // verified
        user.setVerified(!headerElement.findElements(By.className("coreSpriteVerifiedBadge")).isEmpty());

        // stats
        WebElement statsContainer = headerElement.findElement(By.tagName("ul"));

        // posts count
        WebElement postCountContainer = statsContainer.findElement(By.xpath("./li[1]/span/span"));
        user.setPostsCount(parseCount(postCountContainer.getText()));

        // followers & following count
        WebElement followersCountContainer;
        WebElement followingCountContainer;
        if (!statsContainer.findElements(By.tagName("a")).isEmpty()) {
            followersCountContainer = statsContainer.findElement(By.xpath("./li[2]/a/span"));
            followingCountContainer = statsContainer.findElement(By.xpath("./li[3]/a/span"));
        } else {
            followersCountContainer = statsContainer.findElement(By.xpath("./li[2]/span/span"));
            followingCountContainer = statsContainer.findElement(By.xpath("./li[3]/span/span"));
        }
        user.setFollowersCount(parseCount(followersCountContainer.getText()));
        user.setFollowingCount(parseCount(followingCountContainer.getText()));
    }

    private void loadStats() {

    }

    private void loadRecentPosts() {
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

    private static List<UserPostPreviewElement> getUserPostPreviews(WebElement container) {
        List<UserPostPreviewElement> postPreviews = new ArrayList<>();
        List<WebElement> rowContainers = container.findElements(By.xpath("./div"));
        for (WebElement rowContainer : rowContainers) {
            List<WebElement> postContainers = rowContainer.findElements(By.xpath("./div"));
            postPreviews.addAll(postContainers.stream().map(UserPostPreviewElement::new).collect(Collectors.toList()));
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
