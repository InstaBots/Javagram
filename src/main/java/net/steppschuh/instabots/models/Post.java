package net.steppschuh.instabots.models;

import net.steppschuh.instabots.pages.PostPage;
import net.steppschuh.markdowngenerator.text.TextBuilder;

public class Post {

    /**
     * The ID of the post, as included in the URL to the post.
     * Example: "Be7y9UYjz6j"
     */
    private String id;

    /**
     * The source URL of the post image.
     */
    private String imageUrl;

    /**
     * The title of the post, as seen next to the post.
     */
    private String title;

    /**
     * The tags included in the {@link #title}.
     */
    private Tags tags = new Tags();

    /**
     * The total number of people that liked the post.
     */
    private int likesCount;

    /**
     * The total number of people that commented on the post.
     */
    private int commentsCount;

    /**
     * The user that published the post.
     */
    private User user = new User();

    /**
     * The name of the tagged location, if available.
     */
    private String locationName;

    /**
     * Timestamp of the publish date of the post.
     */
    private long timestamp;

    public Post() {
    }

    @Override
    public String toString() {
        return toCompactString();
    }

    public String toCompactString() {
        return "Post " + id;
    }

    public String toDetailedString() {
        TextBuilder textBuilder = new TextBuilder()
                .append("Post by ").append(user.getId())
                .newLine()
                .append(likesCount).append(" likes, ")
                .append(commentsCount).append(" comments")
                .newLine()
                .append(PostPage.getPostUrl(id));

        return textBuilder.toString();
    }

    public static String getTruncatedTitle(String title) {
        return getTruncatedTitle(title, 10);
    }

    public static String getTruncatedTitle(String title, int maximumLength) {
        if (title.length() <= maximumLength) {
            return title;
        }
        return title.substring(0, maximumLength) + "[...]";
    }

    /*
        Getter & Setter
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
