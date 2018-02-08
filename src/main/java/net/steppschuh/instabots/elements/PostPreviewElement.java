package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.pages.PostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public class PostPreviewElement extends InstagramElement {

    protected String postId;
    protected String imageUrl;
    protected String title;
    private int likesCount;
    private int commentsCount;

    public PostPreviewElement(@Nonnull WebElement rootElement) {
        super(rootElement);

        postId = parsePostId();
        imageUrl = parseImageUrl();
        title = parseTitle();
    }

    protected String parsePostId() {
        WebElement postAnchorElement = rootElement.findElement(By.tagName("a"));
        return PostPage.getPostIdFromUrl(postAnchorElement.getAttribute("href"));
    }

    protected String parseImageUrl() {
        WebElement imageElement = rootElement.findElement(By.tagName("img"));
        return imageElement.getAttribute("src");
    }

    protected String parseTitle() {
        WebElement imageElement = rootElement.findElement(By.tagName("img"));
        return imageElement.getAttribute("alt");
    }

    protected int parseLikesCount() {
        return 0; // TODO: parse if possible
    }

    protected int parseCommentsCount() {
        return 0; // TODO: parse if possible
    }

    @Override
    public String toString() {
        return "PostElement " + postId + ": " + Post.getTruncatedTitle(title);
    }

}
