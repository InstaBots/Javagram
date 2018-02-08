package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class PostElement extends InstagramElement {

    private Post post = new Post();

    public PostElement(@Nonnull WebElement rootElement) {
        super(rootElement);

        WebElement imageElement = rootElement.findElement(By.tagName("img"));
        post.setImageUrl(imageElement.getAttribute("src"));
        post.setTitle(imageElement.getAttribute("alt"));

        WebElement headerElement = rootElement.findElement(By.tagName("header"));
        WebElement userIdElement = headerElement.findElement(By.xpath("/div[2]/div[1]/div[1]/a"));
        post.getUser().setId(userIdElement.getText());

        WebElement likesCounterElement = rootElement.findElement(By.tagName("//*[@id=\"react-root\"]/section/main/div/div/article/div[2]/section[2]/div/a/span"));
        post.setLikesCount(Integer.parseInt(likesCounterElement.getText()));
    }

    /*
        Getter & Setter
     */

    public Post getPost() {
        return post;
    }

}
