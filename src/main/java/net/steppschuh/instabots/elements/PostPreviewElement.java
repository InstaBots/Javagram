package net.steppschuh.instabots.elements;

import net.steppschuh.instabots.Javagram;
import net.steppschuh.instabots.models.Post;
import net.steppschuh.instabots.pages.InstagramPage;
import net.steppschuh.instabots.pages.UserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public abstract class PostPreviewElement extends InstagramElement {

    protected Post post;

    public PostPreviewElement(InstagramPage page, WebElement rootElement) {
        super(page, rootElement);
    }

    @Override
    protected void parse() {
        post = new Post();
        post.setType(parseType());
        post.setId(parsePostId());
        post.setImageUrl(parseImageUrl());
        post.setTitle(parseTitle());

        try {
            triggerOverlay();
            post.setLikesCount(parseLikesCount());
            post.setCommentsCount(parseCommentsCount());
        } catch (Exception e) {
            Javagram.LOGGER.log(Level.WARNING, "Unable to parse likes and comments for post preview", e);
        }
    }

    protected Post.Type parseType() {
        if (hasElement(By.className("coreSpriteVideoIconLarge"))) {
            return Post.Type.VIDEO;
        } else if (hasElement(By.className("coreSpriteSidecarIconLarge"))) {
            return Post.Type.GALLERY;
        } else {
            return Post.Type.PHOTO;
        }
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
        WebElement countElement = rootElement.findElement(By.xpath("./a/div[last()]/ul/li[1]/span[1]"));
        return UserPage.parseCount(countElement.getText());
    }

    protected int parseCommentsCount() {
        WebElement countElement = rootElement.findElement(By.xpath("./a/div[last()]/ul/li[2]/span[1]"));
        return UserPage.parseCount(countElement.getText());
    }

    protected void triggerOverlay() {
        // likes and comments are only displayed on mouse-over
        Actions builder = new Actions(page.getChromeDriver());
        builder.moveToElement(rootElement).perform();

        // wait for the overlay to be loaded
        WebDriverWait wait = new WebDriverWait(page.getChromeDriver(), 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("coreSpriteSpeechBubbleSmall")));
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
