package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.utils.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.logging.Level;

public class PostPage extends InstagramPage {

    private Post post = new Post();

    private WebElement postContainer;

    public PostPage(String postId) {
        post.setId(postId);
    }

    @Override
    public String getUrl() {
        return post.getUrl();
    }

    @Override
    public void load() {
        super.load();

        postContainer = chromeDriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article"));

        // type
        if (WebElementUtil.hasElement(postContainer, By.className("videoSpritePlayButton"))) {
            post.setType(Post.Type.VIDEO);
        } else if (WebElementUtil.hasElement(postContainer, By.className("coreSpriteRightChevron"))) {
            post.setType(Post.Type.GALLERY);
        } else {
            post.setType(Post.Type.PHOTO);
        }

        // image URL
        WebElement imageElement = postContainer.findElement(By.tagName("img"));
        post.setImageUrl(imageElement.getAttribute("src"));

        // title
        WebElement titleContainer = postContainer.findElement(By.xpath("./div[2]/div[1]/ul/li[1]/span"));
        post.setTitle(titleContainer.getText());

        // user ID
        WebElement headerElement = postContainer.findElement(By.tagName("header"));
        WebElement userIdElement = headerElement.findElement(By.xpath("./div[2]/div[1]/div[1]/a"));
        post.getUser().setId(userIdElement.getText());

        // timestamp
        WebElement timeElement = postContainer.findElement(By.tagName("time"));
        Instant instant = Instant.parse(timeElement.getAttribute("datetime")); // YYYY-MM-DDThh:mm:ssTZD
        post.setTimestamp(instant.toEpochMilli());

        // likes count
        parseLikesCount();
    }

    public void parseLikesCount() {
        WebElement likesCounterContainer = postContainer.findElement(By.xpath("./div[2]/section[2]/div"));
        WebElement likesCounterElement = null;
        if (WebElementUtil.hasElement(likesCounterContainer, By.xpath("./a/span"))) {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./a/span"));
        } else if (WebElementUtil.hasElement(likesCounterContainer, By.xpath("./span/span"))) {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./span/span"));
        }
        if (likesCounterElement != null) {
            post.setLikesCount(UserPage.parseCount(likesCounterElement.getText()));
        } else {
            post.setLikesCount(likesCounterContainer.findElements(By.tagName("a")).size());
        }
    }

    public boolean isLiked() {
        return WebElementUtil.hasElement(postContainer, By.className("coreSpriteHeartFull"));
    }

    public void like() {
        if (isLiked()) {
            LOGGER.finest("Post already liked: " + post);
            return;
        }

        LOGGER.finest("Liking post: " + post);
        WebElement likeButton = postContainer.findElement(By.className("coreSpriteHeartOpen"));
        likeButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(chromeDriver, 1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coreSpriteHeartFull")));
        } catch (TimeoutException e) {
            LOGGER.log(Level.WARNING, "Unable to like post", e);
        }

        parseLikesCount();
    }

    public void unlike() {
        if (!isLiked()) {
            LOGGER.finest("Post already unliked: " + post);
            return;
        }

        LOGGER.finest("Unliking post: " + post);
        WebElement likeButton = postContainer.findElement(By.className("coreSpriteHeartFull"));
        likeButton.click();

        try {
            WebDriverWait wait = new WebDriverWait(chromeDriver, 1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coreSpriteHeartOpen")));
        } catch (TimeoutException e) {
            LOGGER.log(Level.WARNING, "Unable to unlike post", e);
        }

        parseLikesCount();
    }

    /*
        Getter & Setter
     */

    public Post getPost() {
        return post;
    }

}
