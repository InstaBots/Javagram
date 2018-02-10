package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.models.Post;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

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
        return Post.COUNT_UNKNOWN; // TODO: parse if possible
    }

    protected int parseCommentsCount() {
        return Post.COUNT_UNKNOWN; // TODO: parse if possible
    }

    @Override
    public String toString() {
        return post.toString();
    }

    public static List<Post> getPosts(List<? extends PostPreviewElement> postPreviewElements) {
        List<Post> posts = new ArrayList<>();
        for (PostPreviewElement postPreviewElement : postPreviewElements) {
            posts.add(postPreviewElement.getPost());
        }
        return posts;
    }

    /*
        Getter & Setter
    */

    public Post getPost() {
        return post;
    }

}
