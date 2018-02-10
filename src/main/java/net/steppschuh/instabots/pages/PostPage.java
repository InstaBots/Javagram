package net.steppschuh.instabots.pages;

import net.steppschuh.instabots.models.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.Nonnull;

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

        WebElement imageElement = postContainer.findElement(By.tagName("img"));
        post.setImageUrl(imageElement.getAttribute("src"));

        WebElement titleContainer = postContainer.findElement(By.xpath("./div[2]/div[1]/ul/li[1]/span"));
        post.setTitle(titleContainer.getText());

        WebElement headerElement = postContainer.findElement(By.tagName("header"));
        WebElement userIdElement = headerElement.findElement(By.xpath("./div[2]/div[1]/div[1]/a"));
        post.getUser().setId(userIdElement.getText());

        WebElement likesCounterContainer = postContainer.findElement(By.xpath("./div[2]/section[2]/div"));
        WebElement likesCounterElement;
        if (!likesCounterContainer.findElements(By.tagName("a")).isEmpty()) {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./a/span"));
        } else {
            likesCounterElement = likesCounterContainer.findElement(By.xpath("./span/span"));
        }
        post.setLikesCount(UserPage.parseCount(likesCounterElement.getText()));

    }

    /*
        Getter & Setter
     */

    public Post getPost() {
        return post;
    }

}
