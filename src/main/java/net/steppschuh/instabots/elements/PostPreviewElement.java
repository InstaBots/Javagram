package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.models.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;

public abstract class PostPreviewElement extends InstagramElement {

    protected Post post = new Post();

    public PostPreviewElement(@Nonnull WebElement rootElement) {
        super(rootElement);

        post.setId(parsePostId());
        post.setImageUrl(parseImageUrl());
        post.setTitle(parseTitle());
        post.setLikesCount(parseLikesCount());
        post.setCommentsCount(parseCommentsCount());
    }

    protected String parsePostId() {
        WebElement postAnchorElement = rootElement.findElement(By.tagName("a"));
        return Post.getIdFromUrl(postAnchorElement.getAttribute("href"));
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
        return post.toString();
    }

    /*
        Getter & Setter
    */

    public Post getPost() {
        return post;
    }

}
