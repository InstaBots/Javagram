package net.steppschuh.instabots.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class ExplorePostPreview extends InstagramElement {

    protected String postId;
    protected String imageUrl;
    protected String title;

    public ExplorePostPreview(@Nonnull WebElement rootElement) {
        super(rootElement);

        WebElement postAnchorElement = rootElement.findElement(By.tagName("a"));
        postId = Post.getPostIdFromUrl(postAnchorElement.getAttribute("href"));

        WebElement imageElement = rootElement.findElement(By.tagName("img"));
        imageUrl = imageElement.getAttribute("src");
        title = imageElement.getAttribute("alt");
    }

    @Override
    public String toString() {
        return "Post " + postId + ":\n" + rootElement.getAttribute("innerHTML");
    }

}
