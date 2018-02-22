package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.pages.elements.ExplorePostPreviewElement;
import net.steppschuh.instabots.pages.elements.PostPreviewElement;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ExplorePage extends InstagramPage {

    public static final String EXPLORE_BY_TAG_URL = "https://www.instagram.com/explore/tags/";

    private String tag;
    private int totalPostsCount;

    private List<Post> topPosts = new ArrayList<>();
    private List<Post> recentPosts = new ArrayList<>();

    public ExplorePage(@Nonnull String tag) {
        super();
        this.tag = tag;
    }

    @Override
    public String getUrl() {
        return getExploreByTagUrl(tag);
    }

    @Override
    public void load() {
        super.load();

        WebElement postCountElement = chromeDriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/article/header/span/span"));
        totalPostsCount = UserPage.parseCount(postCountElement.getText());

        if (totalPostsCount > 0) {
            loadTopPosts();
            loadRecentPosts();
        }
    }

    private void loadTopPosts() {
        WebElement topPostsContainer = chromeDriver.findElement(
                By.xpath("//*[@id=\"react-root\"]/section/main/article/div[1]/div/div")
        );
        topPosts = PostPreviewElement.getPosts(getExplorePostPreviews(topPostsContainer));
        LOGGER.finer("Found " + topPosts.size() + " top posts");
        UnorderedList<Post> topPostsList = new UnorderedList<>(topPosts);
        LOGGER.finest("\n" + topPostsList.toString());
    }

    private void loadRecentPosts() {
        WebElement recentPostsContainer = chromeDriver.findElement(
                By.xpath("//*[@id=\"react-root\"]/section/main/article/div[2]/div")
        );
        recentPosts = PostPreviewElement.getPosts(getExplorePostPreviews(recentPostsContainer));
        LOGGER.finer("Found " + recentPosts.size() + " recent posts");
        UnorderedList<Post> recentPostsList = new UnorderedList<>(recentPosts);
        LOGGER.finest("\n" + recentPostsList.toString());
    }

    private List<ExplorePostPreviewElement> getExplorePostPreviews(WebElement container) {
        List<ExplorePostPreviewElement> postPreviews = new ArrayList<>();
        List<WebElement> rowContainers = container.findElements(By.xpath("./div"));
        for (WebElement rowContainer : rowContainers) {
            List<WebElement> postContainers = rowContainer.findElements(By.xpath("./div"));
            for (WebElement postContainer : postContainers) {
                postPreviews.add(new ExplorePostPreviewElement(this, postContainer));
            }
        }
        return postPreviews;
    }

    public void loadMoreRecentPosts() {
        // TODO: implement
    }

    public static String getExploreByTagUrl(@Nonnull String tag) {
        return EXPLORE_BY_TAG_URL + tag + "/";
    }

    /*
        Getter & Setter
     */

    public String getTag() {
        return tag;
    }

    public int getTotalPostsCount() {
        return totalPostsCount;
    }

    public List<Post> getTopPosts() {
        return topPosts;
    }

    public List<Post> getRecentPosts() {
        return recentPosts;
    }

}
