package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.utils.WebElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;
import java.time.Instant;

public class PostPage extends InstagramPage {

    private Post post = new Post();

    public PostPage(@Nonnull ChromeDriver chromeDriver, String postId) {
        super(chromeDriver);
        post.setId(postId);
    }

    @Override
    public String getUrl() {
        return post.getUrl();
    }

    @Override
    public void load() {
        super.load();

        WebElement postContainer = chromeDriver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/article"));

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

        // likes count
        WebElement likesCounterContainer = postContainer.findElement(By.xpath("./div[2]/section[2]/div"));
        WebElement likesCounterElement;
        if (WebElementUtil.hasElement(likesCounterContainer, By.tagName("a"))) {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./a/span"));
        } else {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./span/span"));
        }
        post.setLikesCount(UserPage.parseCount(likesCounterElement.getText()));

        // timestamp
        WebElement timeElement = postContainer.findElement(By.tagName("time"));
        Instant instant = Instant.parse(timeElement.getAttribute("datetime")); // YYYY-MM-DDThh:mm:ssTZD
        post.setTimestamp(instant.toEpochMilli());
    }

    /*
        Getter & Setter
     */

    public Post getPost() {
        return post;
    }

}
