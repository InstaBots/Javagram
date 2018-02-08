package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.elements.ExplorePostPreviewElement;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExplorePage extends InstagramPage {

    public static final String EXPLORE_BY_TAG_URL = "https://www.instagram.com/explore/tags/";

    private String tag;
    private int totalPostsCount;

    private List<ExplorePostPreviewElement> topPosts = new ArrayList<>();
    private List<ExplorePostPreviewElement> recentPosts = new ArrayList<>();

    public ExplorePage(@Nonnull ChromeDriver chromeDriver, @Nonnull String tag) {
        super(chromeDriver);
        this.tag = tag;
    }

    @Override
    public void load() {
        chromeDriver.get(getExploreByTagUrl(tag));

        loadTopPosts();
        loadRecentPosts();
    }

    private void loadTopPosts() {
        WebElement topPostsContainer = chromeDriver.findElement(
                By.xpath("//*[@id=\"react-root\"]/section/main/article/div[1]/div/div")
        );
        topPosts = getExplorePostPreviews(topPostsContainer);
        LOGGER.finer("Found " + topPosts.size() + " top posts");
        UnorderedList<ExplorePostPreviewElement> topPostsList = new UnorderedList<>(topPosts);
        LOGGER.finest(topPostsList.toString());
    }

    private void loadRecentPosts() {
        WebElement recentPostsContainer = chromeDriver.findElement(
                By.xpath("//*[@id=\"react-root\"]/section/main/article/div[2]/div")
        );
        recentPosts = getExplorePostPreviews(recentPostsContainer);
        LOGGER.finer("Found " + recentPosts.size() + " recent posts");
        UnorderedList<ExplorePostPreviewElement> recentPostsList = new UnorderedList<>(recentPosts);
        LOGGER.finest(recentPostsList.toString());
    }

    private List<ExplorePostPreviewElement> getExplorePostPreviews(WebElement container) {
        List<ExplorePostPreviewElement> postPreviews = new ArrayList<>();
        List<WebElement> rowContainers = container.findElements(By.xpath("./div"));
        for (WebElement rowContainer : rowContainers) {
            List<WebElement> postContainers = rowContainer.findElements(By.xpath("./div"));
            postPreviews.addAll(postContainers.stream().map(ExplorePostPreviewElement::new).collect(Collectors.toList()));
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

    public List<ExplorePostPreviewElement> getTopPosts() {
        return topPosts;
    }

    public List<ExplorePostPreviewElement> getRecentPosts() {
        return recentPosts;
    }

}